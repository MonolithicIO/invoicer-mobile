package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class CompanyAddressScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(CompanyAddressState())
    val state = _state.asStateFlow()

    fun resumeState() {
        _state.update {
            it.copy(
                addressLine1 = form.addressLine1,
                addressLine2 = form.addressLine2,
                city = form.city,
                state = form.state,
                postalCode = form.postalCode
            )
        }
        form.countryCode = _state.value.countryCode
    }

    fun setAddressLine1(value: String) {
        _state.update {
            it.copy(
                addressLine1 = value
            )
        }
        form.addressLine1 = value
    }

    fun setAddressLine2(value: String) {
        _state.update {
            it.copy(
                addressLine2 = value
            )
        }
        form.addressLine2 = value
    }

    fun setCity(value: String) {
        _state.update {
            it.copy(
                city = value
            )
        }
        form.city = value
    }

    fun setState(value: String) {
        _state.update {
            it.copy(
                state = value
            )
        }
        form.state = value
    }

    fun setPostalCode(value: String) {
        _state.update {
            it.copy(
                postalCode = value
            )
        }
        form.postalCode = value
    }
}
