package io.github.monolithic.invoicer.features.company.presentation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.invoicer.features.company.presentation.screens.create.CreateCompanyFlow
import io.github.monolithic.invoicer.features.company.presentation.screens.details.CompanyDetailsScreen
import io.github.monolithic.invoicer.features.company.presentation.screens.select.SelectCompanyScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val companyScreens = screenModule {
    register<InvoicerScreen.Company.SelectCompany> {
        SelectCompanyScreen(
            intent = it.intent
        )
    }

    register<InvoicerScreen.Company.CreateCompany> {
        CreateCompanyFlow()
    }

    register<InvoicerScreen.Company.Details> {
        CompanyDetailsScreen()
    }
}
