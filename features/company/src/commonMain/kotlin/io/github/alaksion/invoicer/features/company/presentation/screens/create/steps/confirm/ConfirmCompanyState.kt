package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm.IntermediaryAccountInfo
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm.PayAccountInfo

internal data class ConfirmCompanyState(
    val companyName: String = "",
    val companyDocument: String = "",
    val addressLine1: String = "",
    val addressLine2: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val countryCode: String = "",
    val primaryPayAccount: PayAccountInfo = PayAccountInfo(
        iban = "",
        swift = "",
        bankName = "",
        bankAddress = "",
        shouldContinueToIntermediary = false
    ),
    val intermediaryPayAccount: IntermediaryAccountInfo? = null,
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)
