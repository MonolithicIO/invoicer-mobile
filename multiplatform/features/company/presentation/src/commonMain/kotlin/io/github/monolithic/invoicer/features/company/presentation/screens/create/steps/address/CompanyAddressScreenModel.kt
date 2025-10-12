package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.address

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

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
        _state.updateAndGet {
            it.copy(
                addressLine1 = value.trim()
            )
        }.let {
            form.addressLine1 = it.addressLine1
        }

    }

    fun setAddressLine2(value: String) {
        _state.updateAndGet {
            it.copy(
                addressLine2 = value.trim()
            )
        }.let {
            form.addressLine2 = it.addressLine2
        }
    }

    fun setCity(value: String) {
        _state.updateAndGet {
            it.copy(
                city = value.trim()
            )
        }.let {
            form.city = it.city
        }
    }

    fun setState(value: String) {
        _state.updateAndGet {
            it.copy(
                state = value
            )
        }.let {
            form.state = it.state
        }
    }

    fun setPostalCode(value: String) {
        _state.updateAndGet {
            it.copy(
                postalCode = value
            )
        }.let {
            form.postalCode = it.postalCode
        }
    }
}
