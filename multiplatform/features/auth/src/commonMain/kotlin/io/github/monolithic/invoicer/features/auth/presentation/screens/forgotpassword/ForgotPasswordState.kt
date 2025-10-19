package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword

internal data class ForgotPasswordState(
    val email: String = ""
) {
    val buttonEnabled: Boolean = email.isNotBlank()
}
