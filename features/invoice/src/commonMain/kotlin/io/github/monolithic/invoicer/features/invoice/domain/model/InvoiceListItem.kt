package io.github.monolithic.invoicer.features.invoice.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

data class InvoiceList(
    val items: List<InvoiceListItem>,
    val totalItemCount: Long,
    val nextPage: Long?
)

data class InvoiceListItem(
    val id: String,
    val externalId: String,
    val issueDate: LocalDate,
    val dueDate: LocalDate,
    val createdAt: Instant,
    val updatedAt: Instant,
    val totalAmount: Long,
    val companyName: String,
    val customerName: String
)
