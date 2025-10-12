package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount

internal data class PayAccountState(
    val primaryIban: String = "",
    val primarySwift: String = "",
    val primaryBankName: String = "",
    val primaryBankAddress: String = "",
    val useIntermediaryAccount: Boolean = false
) {

    val isButtonEnabled = primaryIban.isNotBlank() &&
            primarySwift.isNotBlank() &&
            primaryBankName.isNotBlank() &&
            primaryBankAddress.isNotBlank()
}
