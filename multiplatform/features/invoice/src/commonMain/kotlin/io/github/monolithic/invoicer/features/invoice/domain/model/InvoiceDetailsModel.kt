package io.github.monolithic.invoicer.features.invoice.domain.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

internal data class InvoiceDetailsModel(
    val id: String,
    val invoiceNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val issueDate: LocalDate,
    val dueDate: LocalDate,
    val activities: List<InvoiceDetailsActivityModel>,
    val primaryAccount: InvoiceDetailsPayAccountModel,
    val intermediaryAccount: InvoiceDetailsPayAccountModel?,
    val company: InvoiceDetailsCompanyModel,
    val customer: InvoiceDetailsCustomerModel,
)

internal data class InvoiceDetailsPayAccountModel(
    val swift: String,
    val iban: String,
    val bankName: String,
    val bankAddress: String,
)

internal data class InvoiceDetailsCompanyModel(
    val name: String,
    val document: String,
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val zipCode: String,
    val state: String,
    val countryCode: String,
)

internal data class InvoiceDetailsCustomerModel(
    val name: String,
)

internal data class InvoiceDetailsActivityModel(
    val id: String,
    val description: String,
    val unitPrice: Long,
    val quantity: Int
)
