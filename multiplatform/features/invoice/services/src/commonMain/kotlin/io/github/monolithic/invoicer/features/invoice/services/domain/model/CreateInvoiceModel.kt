package io.github.monolithic.invoicer.features.invoice.services.domain.model

import kotlinx.datetime.LocalDate

data class CreateInvoiceModel(
    val invoiceNumber: String,
    val companyId: String,
    val customerId: String,
    val issueDate: LocalDate,
    val dueDate: LocalDate,
    val activities: List<CreateInvoiceActivityModel>
)

data class CreateInvoiceActivityModel(
    val description: String,
    val unitPrice: Long,
    val quantity: Int
)
