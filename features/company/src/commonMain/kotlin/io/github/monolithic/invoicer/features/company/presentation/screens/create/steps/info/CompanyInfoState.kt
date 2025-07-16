package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info

internal data class CompanyInfoState(
    val companyName: String = "",
    val companyDocument: String = ""
) {
    val isButtonEnabled: Boolean
        get() = companyName.isNotBlank() && companyDocument.isNotBlank()
}
