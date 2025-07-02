package io.github.alaksion.invoicer.features.invoice.data.model

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable


@Serializable
internal data class InvoiceDetailsResponse(
    val id: String,
    val invoiceNumber: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val issueDate: LocalDate,
    val dueDate: LocalDate,
    val activities: List<InvoiceDetailsActivityResponse>,
    val primaryAccount: InvoiceDetailsPayAccountResponse,
    val intermediaryAccount: InvoiceDetailsPayAccountResponse?,
    val company: InvoiceDetailsCompanyResponse,
    val customer: InvoiceDetailsCustomerResponse,
)

@Serializable
data class InvoiceDetailsPayAccountResponse(
    val swift: String,
    val iban: String,
    val bankName: String,
    val bankAddress: String,
)

@Serializable
data class InvoiceDetailsCompanyResponse(
    val name: String,
    val document: String,
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val zipCode: String,
    val state: String,
    val countryCode: String,
)

@Serializable
data class InvoiceDetailsCustomerResponse(
    val name: String,
)

@Serializable
internal data class InvoiceDetailsActivityResponse(
    val id: String,
    val description: String,
    val unitPrice: Long,
    val quantity: Int
)
