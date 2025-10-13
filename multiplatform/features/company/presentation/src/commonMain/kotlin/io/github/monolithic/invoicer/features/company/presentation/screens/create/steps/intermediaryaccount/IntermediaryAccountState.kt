package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.intermediaryaccount

internal data class IntermediaryAccountState(
    val primaryIban: String = "",
    val primarySwift: String = "",
    val primaryBankName: String = "",
    val primaryBankAddress: String = "",
) {

    val isButtonEnabled = primaryIban.isNotBlank() &&
            primarySwift.isNotBlank() &&
            primaryBankName.isNotBlank() &&
            primaryBankAddress.isNotBlank()
}
