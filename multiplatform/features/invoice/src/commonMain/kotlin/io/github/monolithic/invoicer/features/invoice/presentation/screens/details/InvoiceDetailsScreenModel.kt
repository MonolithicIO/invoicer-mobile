package io.github.monolithic.invoicer.features.invoice.presentation.screens.details

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.invoice.domain.repository.InvoiceRepository
import io.github.monolithic.invoicer.features.invoice.presentation.model.toUiModel
import io.github.monolithic.invoicer.foundation.network.RequestError
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val NotFound = 400
private const val Forbidden = 403

internal class InvoiceDetailsScreenModel(
    private val invoiceRepository: InvoiceRepository,
    private val dispatcher: CoroutineDispatcher,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceDetailsState())
    val state: StateFlow<InvoiceDetailsState> = _state

    fun initState(
        invoiceId: String
    ) {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                invoiceRepository.getInvoiceDetails(
                    invoiceId = invoiceId,
                    companyId = session.getCompany().id
                )
            }
                .handle(
                    onStart = {
                        _state.update {
                            it.copy(mode = InvoiceDetailsMode.Loading)
                        }
                    },
                    onSuccess = { response ->
                        _state.update { oldState ->
                            oldState.copy(
                                invoiceNumber = response.invoiceNumber,
                                companyName = response.company.name,
                                companyAddress = response.company.addressLine1,
                                customerName = response.customer.name,
                                issueDate = response.issueDate,
                                dueDate = response.dueDate,
                                primaryAccount = response.primaryAccount.toUiModel(),
                                intermediaryAccount = response.intermediaryAccount?.toUiModel(),
                                createdAt = response.createdAt,
                                activities = response.activities,
                                mode = InvoiceDetailsMode.Content
                            )
                        }
                    },
                    onFailure = { requestError ->
                        _state.update { oldState ->
                            oldState.copy(
                                mode = InvoiceDetailsMode.Error(
                                    errorType = requestError.toErrorType()
                                )
                            )
                        }
                    }
                )
        }
    }

    private fun RequestError.toErrorType(): InvoiceDetailsErrorType {
        return when (this) {
            is RequestError.Other -> InvoiceDetailsErrorType.Generic
            is RequestError.Http -> {

                when (this.errorCode) {
                    NotFound, Forbidden -> InvoiceDetailsErrorType.NotFound
                    else -> InvoiceDetailsErrorType.Generic
                }
            }
        }
    }
}
