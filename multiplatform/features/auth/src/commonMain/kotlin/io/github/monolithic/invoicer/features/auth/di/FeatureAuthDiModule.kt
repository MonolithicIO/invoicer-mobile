package io.github.monolithic.invoicer.features.auth.di

import io.github.monolithic.invoicer.features.auth.data.datasource.ResetPasswordDataSource
import io.github.monolithic.invoicer.features.auth.data.datasource.ResetPasswordDataSourceImpl
import io.github.monolithic.invoicer.features.auth.data.repository.ResetPasswordRepositoryImpl
import io.github.monolithic.invoicer.features.auth.domain.repository.ResetPasswordRepository
import io.github.monolithic.invoicer.features.auth.domain.services.ResolveStartupDestinationService
import io.github.monolithic.invoicer.features.auth.domain.services.ResolveStartupDestinationServiceImpl
import io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu.AuthMenuScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.ForgotPasswordScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.screens.startup.StartupScreenModel
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidator
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordStrengthValidatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
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
            signInCommander = get(),
            analyticsTracker = get()
        )
    }

    factory<PasswordStrengthValidator> {
        PasswordStrengthValidatorImpl
    }

    factory<StartupScreenModel> {
        StartupScreenModel(
            startupDestinationService = get(),
            dispatcher = Dispatchers.Default,
            splashScreenDismisser = get()
        )
    }

    factory {
        AuthMenuScreenModel(
            signInCommander = get(),
            dispatcher = Dispatchers.Default,
            analyticsTracker = get()
        )
    }

    factory<ResolveStartupDestinationService> {
        ResolveStartupDestinationServiceImpl(
            authTokenRepository = get(),
            companyRepository = get(),
            selectCompanyService = get(),
        )
    }

    factory<ResetPasswordDataSource> {
        ResetPasswordDataSourceImpl(
            dispatcher = Dispatchers.IO,
            httpClient = get()
        )
    }

    factory<ResetPasswordRepository> {
        ResetPasswordRepositoryImpl(
            dataSource = get()
        )
    }

    factory {
        ForgotPasswordScreenModel(
            dispatcher = Dispatchers.Default,
            resetPasswordRepository = get()
        )
    }
}
