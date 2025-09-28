package io.github.monolithic.features.home.domain.model

import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceListItem

internal data class HomeDetailsModel(
    val top3Invoices: List<InvoiceListItem>
)
