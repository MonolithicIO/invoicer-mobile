package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.customer

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.customer.domain.repository.CustomerRepository
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class InvoiceCustomerScreenModel(
    private val customerRepository: CustomerRepository,
    private val dispatcher: CoroutineDispatcher,
    private val session: Session,
    private val createInvoiceForm: CreateInvoiceForm
) : ScreenModel {

    private var isInitialized = false

    private val _state = MutableStateFlow(InvoiceCustomerState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<Unit>()
    val event = _event.asSharedFlow()

    fun loadCustomers(force: Boolean = false) {
        if (isInitialized && force.not()) return

        screenModelScope.launch(dispatcher) {
            launchRequest {
                customerRepository.listCustomers(
                    companyId = session.company.id,
                    pageSize = 100,
                    page = 0
                )
            }.handle(
                onStart = {
                    _state.value = _state.value.copy(
                        mode = InvoiceCustomerMode.Loading
                    )
                },
                onFailure = {
                    _state.value = _state.value.copy(
                        mode = InvoiceCustomerMode.Loading
                    )
                },
                onSuccess = { response ->
                    _state.value = _state.value.copy(
                        customers = response.items.toPersistentList(),
                        mode = InvoiceCustomerMode.Content
                    )
                    isInitialized = true
                }
            )

        }
    }

    fun selectCustomer(customerId: String?) {
        _state.value = _state.value.copy(
            selectedCustomerId = customerId
        )
    }

    fun submit() {
        if (!_state.value.isButtonEnabled) return
        val customer = _state.value.selectedCustomerId?.let { selectedId ->
            _state.value.customers.firstOrNull { it.id == selectedId }
        } ?: return

        screenModelScope.launch(dispatcher) {
            createInvoiceForm.customerId = customer.id
            createInvoiceForm.customerName = customer.name
            _event.emit(Unit)
        }
    }
}
