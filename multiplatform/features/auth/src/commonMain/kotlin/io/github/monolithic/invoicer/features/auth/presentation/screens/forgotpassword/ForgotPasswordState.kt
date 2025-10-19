package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword

internal data class ForgotPasswordState(
    val email: String = "",
    val isLoading: Boolean = false,
) {
    val buttonEnabled: Boolean = email.isNotBlank()
}

internal sealed interface ForgotPasswordUiEvents {
    data object Success : ForgotPasswordUiEvents
    data class Error(val message: String) : ForgotPasswordUiEvents

}