package io.github.monolithic.invoicer.features.invoice.presentation.navigation

import cafe.adriel.voyager.core.registry.screenModule
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceFlow
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.InvoiceListScreen
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen

val invoiceScreens = screenModule {
    register<InvoicerScreen.Invoices.List> {
        InvoiceListScreen()
    }
    register<InvoicerScreen.Invoices.Create> {
        CreateInvoiceFlow()
    }
}
