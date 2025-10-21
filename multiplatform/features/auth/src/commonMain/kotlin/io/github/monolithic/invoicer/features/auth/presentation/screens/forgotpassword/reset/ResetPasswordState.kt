package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.reset

import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import kotlinx.collections.immutable.PersistentSet
import kotlinx.collections.immutable.persistentSetOf

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
    val passwordsMatch = if (showConfirmPasswordErrors) password == confirmPassword else false
    val passwordError = if (showPasswordErrors) passwordIssues.lastOrNull() else null

    val isFormValid = passwordIssues.isEmpty() && password == confirmPassword
}
