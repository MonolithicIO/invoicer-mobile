package io.github.monolithic.invoicer.features.qrcodeSession.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.home.AuthorizationHomeScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val qrCodeNavigationModule = screenModule {
    register<InvoicerScreen.Authorization.Home> {
        AuthorizationHomeScreen()
    }
}
