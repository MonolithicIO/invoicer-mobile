package io.github.alaksion.invoicer.features.company.presentation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.alaksion.invoicer.features.company.presentation.screens.selectcompany.SelectCompanyScreen
import io.github.alaksion.invoicer.foundation.navigation.InvoicerScreen

val companyScreens = screenModule {
    register<InvoicerScreen.Company.SelectCompany> {
        SelectCompanyScreen()
    }
}