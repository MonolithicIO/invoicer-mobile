package io.github.monolithic.invoicer.sharedApp.init

import io.github.monolithic.invoicer.sharedApp.init.modules.ModuleInitializer
import io.github.monolithic.invoicer.sharedApp.init.modules.common.VoyagerModule

class AppInit(private vararg val initializers: ModuleInitializer) {
    fun startAppModules() {
        VoyagerModule().onStart()
        initializers.forEach { it.onStart() }
    }
}
