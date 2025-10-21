package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.reset

import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue

internal data class ResetPasswordState(
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    private val passwordIssues: Set<PasswordIssue>
) {
    private val passwordsMatch = password == confirmPassword
    val passwordError = passwordIssues.lastOrNull()
}
