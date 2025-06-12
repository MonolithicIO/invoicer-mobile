package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address

internal data class CompanyAddressState(
    val addressLine1: String = "",
    val addressLine2: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
) {
    val countryCode: String = "BRA"

    val isButtonEnabled =
        addressLine1.isNotBlank() &&
                city.isNotBlank() &&
                state.isNotBlank() &&
                postalCode.isNotBlank()
}
