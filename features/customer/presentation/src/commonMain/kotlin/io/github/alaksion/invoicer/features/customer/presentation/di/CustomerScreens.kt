package io.github.alaksion.invoicer.features.customer.presentation.di

import cafe.adriel.voyager.core.registry.screenModule
import io.github.alaksion.invoicer.features.customer.presentation.screens.create.CreateCustomerScreen
import io.github.alaksion.invoicer.foundation.navigation.InvoicerScreen

val customerScreens = screenModule {
    register<InvoicerScreen.Customer.Create> { CreateCustomerScreen() }
}