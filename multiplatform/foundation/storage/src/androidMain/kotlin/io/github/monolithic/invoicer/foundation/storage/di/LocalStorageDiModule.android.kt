package io.github.monolithic.invoicer.foundation.storage.di

import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage
import io.github.monolithic.invoicer.foundation.storage.AndroidLocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformLocalStorageDiModule: Module = module {
    factory<LocalStorage> {
        AndroidLocalStorage(
            context = androidContext()
        )
    }
}
