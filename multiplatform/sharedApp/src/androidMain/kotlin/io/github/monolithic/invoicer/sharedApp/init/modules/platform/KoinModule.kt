package io.github.monolithic.invoicer.sharedApp.init.modules.platform

import android.app.Application
import io.github.monolithic.invoicer.foundation.platform.splashscreen.AndroidSplashScreenDismisser
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisser
import io.github.monolithic.invoicer.sharedApp.init.di.appModule
import io.github.monolithic.invoicer.sharedApp.init.modules.ModuleInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

actual class KoinModule(
    private val application: Application,
) : ModuleInitializer {
    override fun onStart() {
        startKoin {
            androidContext(application)
            modules(
                appModule,
                module {
                    single<SplashScreenDismisser> { AndroidSplashScreenDismisser }
                }
            )
        }
    }
}
