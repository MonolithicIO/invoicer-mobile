package io.github.monolithic.invoicer.features.auth.presentation.utils

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_digit
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_length
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_lowercase
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_special
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_uppercase
import org.jetbrains.compose.resources.stringResource

internal interface PasswordStrengthValidator {
    fun validate(password: String): Set<PasswordIssue>
}

internal object PasswordStrengthValidatorImpl : PasswordStrengthValidator {

    override fun validate(password: String): Set<PasswordIssue> {
        val issues = PasswordIssue.entries.toMutableSet()

        password.forEach {
            when {
                it.isUpperCase() -> issues.remove(PasswordIssue.UPPERCASE)
                it.isLowerCase() -> issues.remove(PasswordIssue.LOWERCASE)
                it.isDigit() -> issues.remove(PasswordIssue.DIGIT)
                !it.isLetterOrDigit() -> issues.remove(PasswordIssue.SPECIAL_CHARACTER)
            }
        }

        if (password.trim().length >= MIN_PASSWORD_LENGTH) {
            issues.remove(PasswordIssue.LENGTH)
        }

        return issues
    }

    private const val MIN_PASSWORD_LENGTH = 8
}


internal enum class PasswordIssue {
    LENGTH,
    UPPERCASE,
    LOWERCASE,
    DIGIT,
    SPECIAL_CHARACTER;
}

@Composable
internal fun PasswordIssue?.textResource() = when (this) {
    PasswordIssue.LENGTH -> stringResource(Res.string.auth_sign_up_weak_password_length)
    PasswordIssue.UPPERCASE -> stringResource(Res.string.auth_sign_up_weak_password_uppercase)
    PasswordIssue.LOWERCASE -> stringResource(Res.string.auth_sign_up_weak_password_lowercase)
    PasswordIssue.DIGIT -> stringResource(Res.string.auth_sign_up_weak_password_digit)
    PasswordIssue.SPECIAL_CHARACTER -> stringResource(Res.string.auth_sign_up_weak_password_special)
    null -> null
}
