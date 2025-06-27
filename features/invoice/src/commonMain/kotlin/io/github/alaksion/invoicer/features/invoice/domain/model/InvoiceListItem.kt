package io.github.alaksion.invoicer.features.invoice.domain.model

import kotlinx.datetime.Instant

data class InvoiceList(
    val items: List<InvoiceListItem>,
    val totalItemCount: Long,
    val nextPage: Long?
)

data class InvoiceListItem(
    val id: String,
    val externalId: String,
    val issueDate: Instant,
    val dueDate: Instant,
    val createdAt: Instant,
    val updatedAt: Instant,
    val totalAmount: Long,
    val companyName: String,
    val customerName: String
)
