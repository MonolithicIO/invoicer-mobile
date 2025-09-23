package io.github.monolithic.features.home.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.features.home.presentation.container.HomeContainerScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val homeContainerScreens = screenModule {
    register<InvoicerScreen.Home> { HomeContainerScreen() }
}
