package io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginAnalytics
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommand
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManager
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.platform.analytics.AnalyticsTracker
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AuthMenuScreenModel(
    private val signInCommander: SignInCommandManager,
    private val dispatcher: CoroutineDispatcher,
    private val analyticsTracker: AnalyticsTracker
) : ScreenModel {

    private val _state = MutableStateFlow(AuthMenuState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<AuthMenuUiEvents>()
    val events = _events.asSharedFlow()

    fun handleGoogleError(error: Throwable) {
        screenModelScope.launch(dispatcher) {
            analyticsTracker.track(LoginAnalytics.GoogleLoginFailure)
            _events.emit(
                AuthMenuUiEvents.SocialLoginError(
                    message = error.message.orEmpty()
                )
            )
        }
    }

    fun handleGoogleSuccess(token: String) {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                signInCommander.resolveCommand(
                    SignInCommand.Google(token)
                )
            }.handle(
                onSuccess = {
                    analyticsTracker.track(LoginAnalytics.GoogleLoginSuccess)
                },
                onFinish = {
                    _state.update {
                        it.copy(
                            mode = AuthMenuMode.Idle
                        )
                    }
                },
                onFailure = { result ->
                    analyticsTracker.track(LoginAnalytics.GoogleLoginFailure)
                    _events.emit(
                        AuthMenuUiEvents.SocialLoginError(
                            message = result.message.orEmpty()
                        )
                    )
                }
            )
        }
    }

    fun launchGoogleLogin() {
        screenModelScope.launch(dispatcher) {
            analyticsTracker.track(LoginAnalytics.GoogleLoginStarted)
            _state.update { it.copy(mode = AuthMenuMode.Loading) }
            _events.emit(AuthMenuUiEvents.LaunchGoogleSignIn)
        }
    }

    fun cancelSocialLogin() {
        _state.update {
            it.copy(
                mode = AuthMenuMode.Idle
            )
        }
    }
}
