package io.github.alaksion.invoicer.sharedApp.init.modules.common

import cafe.adriel.voyager.core.registry.ScreenRegistry
import io.github.alaksion.features.home.presentation.navigation.homeContainerScreens
import io.github.alaksion.invoicer.features.auth.presentation.navigation.authScreens
import io.github.alaksion.invoicer.features.company.presentation.companyScreens
import io.github.alaksion.invoicer.features.customer.presentation.di.customerScreens
import io.github.alaksion.invoicer.features.invoice.presentation.navigation.invoiceScreens
import io.github.alaksion.invoicer.features.qrcodeSession.presentation.navigation.qrCodeNavigationModule
import io.github.alaksion.invoicer.sharedApp.init.modules.ModuleInitializer

internal class VoyagerModule : ModuleInitializer {
    override fun onStart() {
        ScreenRegistry {
            authScreens()
            homeContainerScreens()
            invoiceScreens()
            qrCodeNavigationModule()
            companyScreens()
            customerScreens()
        }
    }
}