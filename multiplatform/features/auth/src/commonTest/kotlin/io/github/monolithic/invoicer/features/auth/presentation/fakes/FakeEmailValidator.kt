package io.github.monolithic.invoicer.features.auth.presentation.fakes

import io.github.monolithic.invoicer.foundation.utils.validation.EmailValidator

class FakeEmailValidator : EmailValidator {

    var response = true

    override fun validate(email: String): Boolean {
        return response
    }
}
