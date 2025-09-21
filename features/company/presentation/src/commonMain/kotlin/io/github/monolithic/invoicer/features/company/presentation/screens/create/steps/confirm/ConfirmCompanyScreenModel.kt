package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm
import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ConfirmCompanyScreenModel(
    private val form: CreateCompanyForm,
    private val repository: CompanyRepository,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _state = MutableStateFlow(ConfirmCompanyState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<CreateCompanyEvents>()
    val events = _events.asSharedFlow()

    fun resumeState() {
        _state.update {
            it.copy(
                companyName = form.companyName,
                companyDocument = form.companyDocument,
                addressLine1 = form.addressLine1,
                addressLine2 = form.addressLine2,
                city = form.city,
                state = form.state,
                postalCode = form.postalCode,
                countryCode = form.countryCode,
                primaryPayAccount = form.primaryPayAccount,
                intermediaryPayAccount = form.intermediaryPayAccount
            )
        }
    }

    fun enableButton() {
        _state.update { it.copy(isButtonEnabled = true) }
    }

    fun createCompany() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                repository.createCompany(
                    data = state.value.toRequestModel()
                )
            }.handle(
                onStart = {
                    _state.update {
                        it.copy(isButtonLoading = true)
                    }
                },
                onFinish = {
                    _state.update {
                        it.copy(isButtonLoading = false)
                    }
                },
                onSuccess = {
                    _events.emit(CreateCompanyEvents.Success)
                },
                onFailure = {
                    _events.emit(CreateCompanyEvents.Error(message = it.message.orEmpty()))
                }
            )
        }
    }
}
