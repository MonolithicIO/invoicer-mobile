package io.github.monolithic.invoicer.sharedApp.init.modules.common

import cafe.adriel.voyager.core.registry.ScreenRegistry
import io.github.monolithic.features.home.presentation.navigation.homeContainerScreens
import io.github.monolithic.invoicer.features.auth.presentation.navigation.authScreens
import io.github.monolithic.invoicer.features.company.presentation.companyScreens
import io.github.monolithic.invoicer.features.customer.presentation.di.customerScreens
import io.github.monolithic.invoicer.features.invoice.presentation.navigation.invoiceScreens
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.navigation.qrCodeNavigationModule
import io.github.monolithic.invoicer.sharedApp.init.modules.ModuleInitializer

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
