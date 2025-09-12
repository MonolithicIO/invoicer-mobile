package io.github.monolithic.invoicer.features.auth.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu.AuthMenuScreen
import io.github.monolithic.invoicer.features.auth.presentation.screens.startup.StartupScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val authScreens = screenModule {
    register<InvoicerScreen.Auth.AuthMenu> {
        AuthMenuScreen()
    }

    register<InvoicerScreen.Auth.Startup> {
        StartupScreen()
    }
}
