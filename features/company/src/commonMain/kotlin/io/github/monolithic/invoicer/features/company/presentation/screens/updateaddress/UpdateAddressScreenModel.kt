package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.company.domain.model.UpdateAddressModel
import io.github.monolithic.invoicer.features.company.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UpdateAddressScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val companyRepository: CompanyRepository,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(UpdateAddressState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<UpdateAddressEvent>()
    val events = _events.asSharedFlow()

    fun initState(
        args: UpdateAddressScreen.Args
    ) {
        _state.update {
            it.copy(
                addressLine = args.addressLine,
                addressLine2 = args.addressLine2,
                city = args.city,
                state = args.state,
                postalCode = args.postalCode
            )
        }
    }

    fun updateAddressLine(addressLine: String) {
        _state.update { it.copy(addressLine = addressLine) }
    }

    fun updateAddressLine2(addressLine2: String) {
        _state.update { it.copy(addressLine2 = addressLine2.ifBlank { null }) }
    }

    fun updateCity(city: String) {
        _state.update { it.copy(city = city) }
    }

    fun updateState(state: String) {
        _state.update { it.copy(state = state) }
    }

    fun updatePostalCode(postalCode: String) {
        _state.update { it.copy(postalCode = postalCode) }
    }

    fun submit() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                companyRepository.updateAddress(
                    companyId = session.getCompany().id,
                    model = UpdateAddressModel(
                        addressLine = _state.value.addressLine,
                        addressLine2 = _state.value.addressLine2,
                        city = _state.value.city,
                        state = _state.value.state,
                        postalCode = _state.value.postalCode
                    )
                )
            }.handle(
                onStart = {
                    _state.update { it.copy(isButtonLoading = true) }
                },
                onFinish = {
                    _state.update { it.copy(isButtonLoading = false) }
                },
                onSuccess = {
                    _events.emit(UpdateAddressEvent.Success)
                },
                onFailure = {
                    _events.emit(UpdateAddressEvent.Error(it.message ?: ""))
                }
            )
        }
    }
}
