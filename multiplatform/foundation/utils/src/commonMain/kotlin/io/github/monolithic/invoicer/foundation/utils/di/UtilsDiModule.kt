package io.github.monolithic.invoicer.foundation.utils.di

import io.github.monolithic.invoicer.foundation.utils.validation.EmailValidator
import io.github.monolithic.invoicer.foundation.utils.validation.EmailValidatorImpl
import io.github.monolithic.invoicer.foundation.utils.validation.UuidValidator
import io.github.monolithic.invoicer.foundation.utils.validation.UuidValidatorImpl
import kotlinx.datetime.Clock
import org.koin.core.module.Module
import org.koin.dsl.module

val utilsDiModule = module {
    includes(utilPlatformModule)
    factory<Clock> { Clock.System }

    factory<UuidValidator> { UuidValidatorImpl() }

    factory<EmailValidator> { EmailValidatorImpl() }
}

internal expect val utilPlatformModule: Module
