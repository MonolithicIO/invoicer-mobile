package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.reset

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.auth.domain.repository.ResetPasswordRepository
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidator
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import kotlinx.collections.immutable.toPersistentSet
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ResetPasswordScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val resetPasswordRepository: ResetPasswordRepository,
    private val passwordStrengthValidator: PasswordStrengthValidator
) : ScreenModel {

    private val _state = MutableStateFlow(ResetPasswordState())
    val state = _state.asStateFlow()

    fun updatePassword(newValue: String) {
        _state.update {
            it.copy(
                password = newValue.trim(),
                passwordIssues = passwordStrengthValidator
                    .validate(newValue)
                    .toPersistentSet(),
                showPasswordErrors = false
            )
        }
    }

    fun togglePasswordCensorship() = _state.update {
        it.copy(
            isPasswordCensored = it.isPasswordCensored.not()
        )
    }

    fun toggleConfirmPasswordCensorship() = _state.update {
        it.copy(
            isConfirmPasswordCensored = it.isConfirmPasswordCensored.not()
        )
    }

    fun updateConfirmPassword(newValue: String) {
        _state.update {
            it.copy(
                confirmPassword = newValue.trim(),
                showConfirmPasswordErrors = false
            )
        }
    }


    fun submit(token: String) {
        if (state.value.isFormValid) {
            screenModelScope.launch(dispatcher) {
                launchRequest {
                    resetPasswordRepository.submitResetPassword(
                        resetToken = token,
                        confirmPassword = state.value.confirmPassword,
                        newPassword = state.value.password
                    )
                }.handle(
                    onStart = { _state.update { it.copy(isLoading = true) } },
                    onFinish = { _state.update { it.copy(isLoading = false) } }
                )
            }

        } else {
            _state.update {
                it.copy(
                    showPasswordErrors = true,
                    showConfirmPasswordErrors = true
                )
            }
        }
    }

}