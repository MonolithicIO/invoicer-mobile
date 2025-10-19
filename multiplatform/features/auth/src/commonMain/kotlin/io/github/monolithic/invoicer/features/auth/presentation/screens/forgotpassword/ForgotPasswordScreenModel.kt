package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.auth.domain.repository.ResetPasswordRepository
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ForgotPasswordScreenModel(
    private val resetPasswordRepository: ResetPasswordRepository,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _state = MutableStateFlow(ForgotPasswordState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<ForgotPasswordUiEvents>()
    val events = _events.asSharedFlow()

    fun updateEmail(email: String) {
        _state.update { it.copy(email = email) }
    }

    fun submit() {
        if (state.value.buttonEnabled) {
            screenModelScope.launch(dispatcher) {
                launchRequest {
                    resetPasswordRepository.requestPasswordReset(
                        email = state.value.email.trim()
                    )
                }.handle(
                    onStart = {
                        _state.update {
                            it.copy(
                                isLoading = true
                            )
                        }
                    },
                    onSuccess = {
                        _events.emit(
                            value = ForgotPasswordUiEvents.Success
                        )
                    },
                    onFinish = {
                        _state.update {
                            it.copy(
                                isLoading = false
                            )
                        }
                    },
                    onFailure = { failure ->
                        _events.emit(
                            value = ForgotPasswordUiEvents.Error(
                                message = failure.message.orEmpty()
                            )
                        )
                    }
                )
            }
        }
    }
}
