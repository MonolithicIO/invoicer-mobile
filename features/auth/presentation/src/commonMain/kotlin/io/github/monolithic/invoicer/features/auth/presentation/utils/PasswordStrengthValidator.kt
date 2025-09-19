package io.github.monolithic.invoicer.features.auth.presentation.utils

interface PasswordStrengthValidator {
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


enum class PasswordIssue {
    LENGTH,
    UPPERCASE,
    LOWERCASE,
    DIGIT,
    SPECIAL_CHARACTER
}