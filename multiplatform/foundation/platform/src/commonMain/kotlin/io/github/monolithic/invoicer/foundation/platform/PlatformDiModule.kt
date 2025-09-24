package io.github.monolithic.invoicer.foundation.platform

import org.koin.core.module.Module
import org.koin.dsl.module

val platformDiModule = module {
    includes(platformNativeModule)
}

internal expect val platformNativeModule: Module