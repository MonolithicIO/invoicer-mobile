package io.github.monolithic.invoicer.features.customer.presentation.di

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.invoicer.features.customer.presentation.screens.create.CreateCustomerScreen
import io.github.monolithic.invoicer.features.customer.presentation.screens.list.CustomerListScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val customerScreens = screenModule {
    register<InvoicerScreen.Customer.Create> { CreateCustomerScreen() }

    register<InvoicerScreen.Customer.List> { CustomerListScreen() }
}
