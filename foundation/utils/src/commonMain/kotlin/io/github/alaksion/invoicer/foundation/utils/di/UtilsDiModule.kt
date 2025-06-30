package io.github.alaksion.invoicer.foundation.utils.di

import kotlinx.datetime.Clock
import org.koin.core.module.Module
import org.koin.dsl.module

val utilsDiModule = module {
    includes(utilPlatformModule)
    factory<Clock> { Clock.System }
}

internal expect val utilPlatformModule: Module