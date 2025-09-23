package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress

internal data class UpdateAddressState(
    val addressLine: String = "",
    val addressLine2: String? = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val isButtonLoading: Boolean = false
) {
    val isButtonEnabled: Boolean =
        addressLine.isNotBlank() &&
                city.isNotBlank() &&
                state.isNotBlank() &&
                postalCode.isNotBlank()
}

sealed interface UpdateAddressEvent {
    data class Error(val message: String) : UpdateAddressEvent
    data object Success : UpdateAddressEvent
}
