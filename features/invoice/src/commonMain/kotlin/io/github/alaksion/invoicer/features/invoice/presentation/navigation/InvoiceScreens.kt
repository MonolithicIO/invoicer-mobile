package io.github.alaksion.invoicer.features.invoice.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceFlow
import io.github.alaksion.invoicer.features.invoice.presentation.screens.invoicelist.InvoiceListScreen
import io.github.alaksion.invoicer.foundation.navigation.InvoicerScreen

val invoiceScreens = screenModule {
    register<InvoicerScreen.Invoices.List> {
        InvoiceListScreen()
    }
    register<InvoicerScreen.Invoices.Create> {
        CreateInvoiceFlow()
    }
}
