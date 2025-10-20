package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.otp

internal data class ForgotPasswordOtpState(
    val otpCode: String = "",
    val isLoading: Boolean = false,
) {
    val isButtonEnabled: Boolean = false
}

internal sealed interface ForgotPasswordOtpUiEvents {
    data class Success(val resetToken: String) : ForgotPasswordOtpUiEvents
    data class Failure(val reason: String) : ForgotPasswordOtpUiEvents
}
