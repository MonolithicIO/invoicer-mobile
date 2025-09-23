package io.github.monolithic.invoicer.foundation.storage.di

import org.koin.core.module.Module
import org.koin.dsl.module

val localStorageDiModule = module {
    includes(platformLocalStorageDiModule)
}

internal expect val platformLocalStorageDiModule: Module
