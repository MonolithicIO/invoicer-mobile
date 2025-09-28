package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.monolithic.invoicer.features.invoice.services.domain.model.CreateInvoiceActivityModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.CreateInvoiceModel
import io.github.monolithic.invoicer.features.invoice.services.domain.repository.InvoiceRepository
import io.github.monolithic.invoicer.foundation.auth.session.Session
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.utils.date.toLocalDate
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.HomeRefreshBus
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.TimeZone

internal class InvoiceConfirmationScreenModel(
    private val form: CreateInvoiceForm,
    private val session: Session,
    private val invoiceRepository: InvoiceRepository,
    private val dispatcher: CoroutineDispatcher,
    private val homeRefreshBus: HomeRefreshBus
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfirmationState())
    val state: StateFlow<InvoiceConfirmationState> = _state

    private val _events = MutableSharedFlow<InvoiceConfirmationEvent>()
    val events = _events.asSharedFlow()

    fun initState() {
        _state.update {
            it.copy(
                companyName = session.getCompany().name,
                customerName = form.customerName,
                issueDate = form.issueDate.toLocalDate(TimeZone.UTC),
                dueDate = form.dueDate.toLocalDate(TimeZone.UTC),
                invoiceNumber = form.invoiceNumber,
                activities = form.activities
            )
        }
    }

    fun submitInvoice() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                invoiceRepository.createInvoice(
                    payload = CreateInvoiceModel(
                        companyId = session.getCompany().id,
                        customerId = form.customerId,
                        issueDate = form.issueDate.toLocalDate(TimeZone.UTC),
                        dueDate = form.dueDate.toLocalDate(TimeZone.UTC),
                        invoiceNumber = form.invoiceNumber,
                        activities = form.activities.map {
                            CreateInvoiceActivityModel(
                                description = it.description,
                                quantity = it.quantity,
                                unitPrice = it.unitPrice,
                            )
                        }
                    )
                )
            }.handle(
                onStart = {
                    _state.update {
                        it.copy(isLoading = true)
                    }
                },
                onFinish = {
                    _state.update {
                        it.copy(isLoading = false)
                    }
                },
                onSuccess = {
                    homeRefreshBus.publishEvent(Unit)
                    _events.emit(InvoiceConfirmationEvent.Success)
                },
                onFailure = {
                    _events.emit(InvoiceConfirmationEvent.Error(it.message.orEmpty()))
                }
            )
        }
    }
}
