package io.github.monolithic.invoicer.features.auth.presentation.di

import io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu.AuthMenuScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.startup.StartupScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.utils.EmailValidator
import io.github.monolithic.invoicer.features.auth.presentation.utils.EmailValidatorImpl
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidator
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidatorImpl
import kotlinx.coroutines.Dispatchers
import org.koin.core.module.Module
import org.koin.dsl.module

val featureAuthPresentationDiModule = module {
    viewModelBindings()
}

private fun Module.viewModelBindings() {
    factory {
        SignUpScreenModel(
            authRepository = get(),
            dispatcher = Dispatchers.Default,
            emailValidator = get(),
            passwordStrengthValidator = get(),
            analyticsTracker = get()
        )
    }

    factory {
        LoginScreenModel(
            dispatcher = Dispatchers.Default,
            signInCommander = get(),
            analyticsTracker = get()
        )
    }

    factory<PasswordStrengthValidator> {
        PasswordStrengthValidatorImpl
    }

    factory<StartupScreenModel> {
        StartupScreenModel(
            resumeSessionService = get()
        )
    }

    factory<EmailValidator> { EmailValidatorImpl() }

    factory {
        AuthMenuScreenModel(
            signInCommander = get(),
            dispatcher = Dispatchers.Default,
            analyticsTracker = get()
        )
    }
}
