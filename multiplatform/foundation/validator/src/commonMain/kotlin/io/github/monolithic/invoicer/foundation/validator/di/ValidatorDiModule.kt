package io.github.monolithic.invoicer.foundation.validator.di

import io.github.monolithic.invoicer.foundation.validator.UuidValidator
import io.github.monolithic.invoicer.foundation.validator.UuidValidatorImpl
import org.koin.dsl.module

val validatorDiModule = module {
    factory<UuidValidator> { UuidValidatorImpl() }
}
