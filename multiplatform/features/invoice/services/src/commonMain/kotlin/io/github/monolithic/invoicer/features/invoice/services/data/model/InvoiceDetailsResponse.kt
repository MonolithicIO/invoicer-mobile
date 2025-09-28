package io.github.monolithic.invoicer.features.invoice.services.data.model

import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsActivityModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsCompanyModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsCustomerModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsPayAccountModel
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
internal data class InvoiceDetailsPayAccountResponse(
    val swift: String,
    val iban: String,
    val bankName: String,
    val bankAddress: String,
)

@Serializable
internal data class InvoiceDetailsCompanyResponse(
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
internal data class InvoiceDetailsCustomerResponse(
    val name: String,
)

@Serializable
internal data class InvoiceDetailsActivityResponse(
    val id: String,
    val description: String,
    val unitPrice: Long,
    val quantity: Int
)

internal fun InvoiceDetailsResponse.toModel(): InvoiceDetailsModel {
    return InvoiceDetailsModel(
        id = id,
        invoiceNumber = invoiceNumber,
        createdAt = createdAt,
        updatedAt = updatedAt,
        issueDate = issueDate,
        dueDate = dueDate,
        activities = activities.map {
            InvoiceDetailsActivityModel(
                id = it.id,
                description = it.description,
                unitPrice = it.unitPrice,
                quantity = it.quantity
            )
        },
        primaryAccount = InvoiceDetailsPayAccountModel(
            swift = primaryAccount.swift,
            iban = primaryAccount.iban,
            bankName = primaryAccount.bankName,
            bankAddress = primaryAccount.bankAddress
        ),
        intermediaryAccount = intermediaryAccount?.let { intermediaryAccount ->
            InvoiceDetailsPayAccountModel(
                swift = intermediaryAccount.swift,
                iban = intermediaryAccount.iban,
                bankName = intermediaryAccount.bankName,
                bankAddress = intermediaryAccount.bankAddress
            )
        },
        company = InvoiceDetailsCompanyModel(
            name = company.name,
            document = company.document,
            addressLine1 = company.addressLine1,
            addressLine2 = company.addressLine2,
            city = company.city,
            zipCode = company.zipCode,
            state = company.state,
            countryCode = company.countryCode
        ),
        customer = InvoiceDetailsCustomerModel(
            name = customer.name
        )
    )
}
