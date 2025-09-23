package io.github.monolithic.invoicer.foundation.analytics.di

import org.koin.core.module.Module
import org.koin.dsl.module

val analyticsDiModule = module {
    includes(analyticsPlatformModule)
}

internal expect val analyticsPlatformModule: Module
