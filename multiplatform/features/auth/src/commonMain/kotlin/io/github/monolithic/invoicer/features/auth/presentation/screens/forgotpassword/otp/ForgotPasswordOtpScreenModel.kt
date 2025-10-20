package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.otp

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

internal class ForgotPasswordOtpScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: ResetPasswordRepository
) : ScreenModel {

    private val _state = MutableStateFlow(ForgotPasswordOtpState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<ForgotPasswordOtpUiEvents>()
    val events = _events.asSharedFlow()

    fun onChangeOtpCode(newValue: String) {
        _state.update { it.copy(otpCode = newValue) }
    }

    fun submit(requestId: String) {
        if (_state.value.isButtonEnabled) {
            screenModelScope.launch(dispatcher) {
                launchRequest {
                    repository.verifyResetPassword(
                        pinCode = state.value.otpCode,
                        requestCode = requestId
                    )
                }.handle(
                    onStart = {
                        _state.update { it.copy(isLoading = true) }
                    },
                    onSuccess = { response ->
                        _events.emit(
                            ForgotPasswordOtpUiEvents.Success(
                                resetToken = response
                            )
                        )
                    },
                    onFinish = {
                        _state.update { it.copy(isLoading = false) }
                    },
                    onFailure = { error ->
                        _events.emit(
                            ForgotPasswordOtpUiEvents.Failure(
                                reason = error.message.orEmpty()
                            )
                        )
                    }
                )
            }
        }
    }
}
