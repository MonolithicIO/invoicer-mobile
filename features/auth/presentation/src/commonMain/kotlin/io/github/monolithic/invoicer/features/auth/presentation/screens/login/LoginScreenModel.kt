package io.github.monolithic.invoicer.features.auth.presentation.screens.login

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.foundation.analytics.AnalyticsTracker
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommand
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManager
import io.github.monolithic.invoicer.foundation.network.RequestError
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class LoginScreenModel(
    private val signInCommander: SignInCommandManager,
    private val dispatcher: CoroutineDispatcher,
    private val analyticsTracker: AnalyticsTracker
) : ScreenModel {

    private val _state = MutableStateFlow(LoginScreenState())
    val state: StateFlow<LoginScreenState> = _state

    private val _events = MutableSharedFlow<LoginScreenEvents>()
    val events = _events.asSharedFlow()

    fun onEmailChanged(email: String) {
        _state.value = _state.value.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        _state.value = _state.value.copy(password = password)
    }

    fun toggleCensorship() {
        _state.value = _state.value.copy(censored = !_state.value.censored)
    }

    fun submitIdentityLogin() {
        if (_state.value.buttonEnabled) handleSignInRequest()
    }

    private fun handleSignInRequest() {
        screenModelScope.launch {
            launchRequest {
                signInCommander.resolveCommand(
                    SignInCommand.Credential(
                        userName = _state.value.email,
                        password = _state.value.password
                    )
                )
            }.handle(
                onStart = {
                    analyticsTracker.track(LoginAnalytics.IdentityLoginStarted)
                    _state.update {
                        it.copy(isSignInLoading = true)
                    }
                },
                onFinish = { _state.update { it.copy(isSignInLoading = false) } },
                onFailure = {
                    analyticsTracker.track(LoginAnalytics.IdentityLoginFailure)
                    sendErrorEvent(it)
                },
                onSuccess = {
                    analyticsTracker.track(LoginAnalytics.IdentityLoginSuccess)
                }
            )
        }
    }

    private suspend fun sendErrorEvent(error: RequestError) {
        val message = when (error) {
            is RequestError.Http -> error.message?.let {
                LoginScreenEvents.Failure(it)
            } ?: LoginScreenEvents.GenericFailure

            is RequestError.Other -> LoginScreenEvents.GenericFailure
        }
        _events.emit(message)
    }

}
