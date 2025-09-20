package io.github.monolithic.invoicer.features.auth.presentation.screens.signup

import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import kotlinx.collections.immutable.ImmutableSet
import kotlinx.collections.immutable.persistentSetOf

internal data class SignUpScreenState(
    val email: String = "",
    val password: String = "",
    val censored: Boolean = true,
    val requestLoading: Boolean = false,
    val emailValid: Boolean = true,
    val duplicateEmails: ImmutableSet<String> = persistentSetOf(),
    private val passwordIssues: ImmutableSet<PasswordIssue> = persistentSetOf(),
    private val submitAttempted: Boolean = false
) {
    val buttonEnabled: Boolean =
        if (submitAttempted) {
            email.isNotBlank()
                    && password.isNotBlank()
                    && emailValid
                    && requestLoading.not()
                    && passwordIssues.isEmpty()
        } else {
            true
        }

    val currentPasswordIssue: PasswordIssue? =
        if (submitAttempted) {
            passwordIssues.firstOrNull()
        } else {
            null
        }

    val currentEmailIssue: SignUpEmailIssue? =
        if (submitAttempted) {
            when {
                duplicateEmails.contains(email) -> SignUpEmailIssue.DuplicateAccount
                emailValid.not() -> SignUpEmailIssue.InvalidFormat
                else -> null
            }
        } else {
            null
        }
}

internal enum class SignUpEmailIssue {
    InvalidFormat,
    DuplicateAccount
}

internal sealed interface SignUpEvents {
    data object Success : SignUpEvents
    data object GenericFailure : SignUpEvents
    data class Failure(val message: String) : SignUpEvents
    data object DuplicateAccount : SignUpEvents
}
