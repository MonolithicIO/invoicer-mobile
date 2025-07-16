package io.github.monolithic.invoicer

import android.app.Application
import io.github.monolithic.invoicer.sharedApp.init.AppInit
import io.github.monolithic.invoicer.sharedApp.init.modules.platform.KoinModule

class InvoicerApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        AppInit(
            KoinModule(this)
        ).startAppModules()
    }
}