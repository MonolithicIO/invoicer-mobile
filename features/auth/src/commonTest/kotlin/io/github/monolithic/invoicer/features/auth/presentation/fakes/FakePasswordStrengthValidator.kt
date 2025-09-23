package io.github.monolithic.invoicer.features.auth.presentation.fakes

import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidator

class FakePasswordStrengthValidator : PasswordStrengthValidator {


    override fun validate(password: String): Set<PasswordIssue> {
        return setOf()
    }
}
