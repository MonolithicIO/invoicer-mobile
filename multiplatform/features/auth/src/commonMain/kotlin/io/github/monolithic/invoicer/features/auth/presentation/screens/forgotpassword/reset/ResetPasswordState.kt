package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.reset

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_confirm_password_blank_error
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_confirm_password_mismatch_error
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_password_blank_error
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import io.github.monolithic.invoicer.features.auth.presentation.utils.textResource
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf
import org.jetbrains.compose.resources.stringResource

internal data class ResetPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val isPasswordCensored: Boolean = true,
    val isConfirmPasswordCensored: Boolean = true,
    private val passwordIssues: PersistentSet<PasswordIssue> = persistentSetOf(),
    private val showPasswordErrors: Boolean = false,
    private val showConfirmPasswordErrors: Boolean = false
) {
    private val innerPasswordState = when {
        password.isBlank() -> PasswordState.Blank
        passwordIssues.isNotEmpty() -> passwordIssues.firstOrNull()?.let {
            PasswordState.Weak(it)
        } ?: PasswordState.Ok

        else -> PasswordState.Ok
    }

    private val innerConfirmPasswordState = when {
        confirmPassword.isBlank() -> ConfirmPasswordState.Blank
        confirmPassword != password -> ConfirmPasswordState.Mismatch
        else -> ConfirmPasswordState.Ok
    }
    val confirmPasswordState = if (showConfirmPasswordErrors) innerConfirmPasswordState
    else ConfirmPasswordState.Ok

    val passwordState = if (showPasswordErrors) innerPasswordState
    else PasswordState.Ok

    val isFormValid = innerPasswordState is PasswordState.Ok &&
            innerConfirmPasswordState == ConfirmPasswordState.Ok
}

internal sealed interface PasswordState {
    data object Blank : PasswordState
    data object Ok : PasswordState
    data class Weak(val issue: PasswordIssue) : PasswordState

    @Composable
    fun getText(): String? = when (this) {
        Blank -> stringResource(Res.string.forgot_password_reset_password_blank_error)
        Ok -> null
        is Weak -> this.issue.textResource()
    }

}

internal enum class ConfirmPasswordState {
    Blank,
    Mismatch,
    Ok;

    @Composable
    fun getText(): String? = when (this) {
        Blank -> stringResource(
            resource = Res.string.forgot_password_reset_confirm_password_blank_error
        )

        Mismatch -> stringResource(
            resource = Res.string.forgot_password_reset_confirm_password_mismatch_error
        )

        Ok -> null
    }
}

internal sealed interface ResetPasswordUiEvents {
    data object Success : ResetPasswordUiEvents
    data class Failure(val message: String) : ResetPasswordUiEvents
}
