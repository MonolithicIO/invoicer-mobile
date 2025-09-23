package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation

internal data class AuthorizationConfirmationState(
    val qrCodeAgent: String = "",
    val qrCodeIp: String = "",
    val qrCodeExpiration: String = "",
    val qrCodeEmission: String = "",
    val mode: AuthorizationConfirmationMode = AuthorizationConfirmationMode.Content
)

internal sealed interface AuthorizationConfirmationMode {
    data object Loading : AuthorizationConfirmationMode
    data object Content : AuthorizationConfirmationMode
    data object Error : AuthorizationConfirmationMode
    data object AuthorizeError : AuthorizationConfirmationMode
}

internal enum class AuthorizationConfirmationEvents {
    Authorized;
}
