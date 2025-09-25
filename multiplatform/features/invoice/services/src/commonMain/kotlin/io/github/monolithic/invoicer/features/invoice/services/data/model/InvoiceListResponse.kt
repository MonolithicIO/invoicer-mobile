package io.github.monolithic.invoicer.features.invoice.services.data.model

import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceList
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceListItem
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
internal data class InvoiceListResponse(
    val items: List<InvoiceListItemResponse>,
    val totalItemCount: Long,
    val nextPage: Long?
)

@Serializable
internal data class InvoiceListItemResponse(
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

internal fun InvoiceListResponse.toDomainModel() = InvoiceList(
    items = items.map {
        InvoiceListItem(
            id = it.id,
            externalId = it.externalId,
            issueDate = it.issueDate,
            dueDate = it.dueDate,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt,
            totalAmount = it.totalAmount,
            companyName = it.companyName,
            customerName = it.customerName,
        )
    },
    totalItemCount = totalItemCount,
    nextPage = nextPage
)
