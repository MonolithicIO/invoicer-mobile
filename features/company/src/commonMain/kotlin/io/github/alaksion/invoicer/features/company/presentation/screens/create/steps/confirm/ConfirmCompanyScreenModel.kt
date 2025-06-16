package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class ConfirmCompanyScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(ConfirmCompanyState())
    val state = _state.asStateFlow()

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
}
