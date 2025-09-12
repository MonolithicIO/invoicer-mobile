package io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu

internal data class AuthMenuState(
    val mode: AuthMenuMode = AuthMenuMode.Idle
)

internal sealed interface AuthMenuUiEvents {
    data class SocialLoginError(val message: String) : AuthMenuUiEvents
    data object LaunchGoogleSignIn : AuthMenuUiEvents
}

internal enum class AuthMenuMode {
    Idle,
    Loading,
}