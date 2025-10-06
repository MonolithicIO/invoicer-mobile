package io.github.monolithic.invoicer.features.customer.presentation.screens.create

internal data class CreateCustomerState(
    val phone: String = "",
    val email: String = "",
    val name: String = "",
    val isButtonLoading: Boolean = false,
    val phoneValid: Boolean = true,
    val emailValid: Boolean = true,
    val nameValid: Boolean = true,
)

internal sealed interface CreateCustomerEvent {
    data object Success : CreateCustomerEvent
    data class Failure(val message: String) : CreateCustomerEvent
}
