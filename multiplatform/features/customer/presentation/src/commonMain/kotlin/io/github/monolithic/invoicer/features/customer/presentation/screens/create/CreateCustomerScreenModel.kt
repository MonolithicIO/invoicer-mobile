package io.github.monolithic.invoicer.features.customer.presentation.screens.create

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.customer.domain.model.CreateCustomerModel
import io.github.monolithic.invoicer.features.customer.domain.repository.CustomerRepository
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.auth.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CreateCustomerScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val customerRepository: CustomerRepository,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(CreateCustomerState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<CreateCustomerEvent>()
    val events = _events.asSharedFlow()

    fun updateName(name: String) {
        _state.update {
            it.copy(
                name = name
            )
        }
    }

    fun updateEmail(email: String) {
        _state.update {
            it.copy(
                email = email
            )
        }
    }

    fun updatePhone(phone: String) {
        _state.update {
            it.copy(
                phone = phone
            )
        }
    }

    fun submit() {
        if (state.value.isButtonEnabled.not()) return

        screenModelScope.launch(dispatcher) {
            launchRequest {
                customerRepository.createCustomer(
                    model = CreateCustomerModel(
                        name = state.value.name,
                        email = state.value.email,
                        phone = state.value.phone.ifBlank { null },
                        companyId = session.getCompany().id
                    )
                )
            }.handle(
                onStart = {
                    _state.update { it.copy(isButtonLoading = true) }
                },
                onSuccess = {
                    _events.emit(CreateCustomerEvent.Success)
                },
                onFinish = {
                    _state.update { it.copy(isButtonLoading = false) }
                },
                onFailure = { failure ->
                    _events.emit(CreateCustomerEvent.Failure(failure.message.orEmpty()))
                }
            )
        }
    }
}
