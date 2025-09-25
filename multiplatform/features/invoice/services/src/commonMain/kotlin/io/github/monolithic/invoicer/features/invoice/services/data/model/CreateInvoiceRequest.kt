package io.github.monolithic.invoicer.features.invoice.services.data.model

import io.github.monolithic.invoicer.features.invoice.services.domain.model.CreateInvoiceModel
import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
internal data class CreateInvoiceRequest(
    val invoiceNumber: String,
    val companyId: String,
    val customerId: String,
    val issueDate: LocalDate,
    val dueDate: LocalDate,
    val activities: List<CreateInvoiceActivityRequest>,
)

@Serializable
internal data class CreateInvoiceActivityRequest(
    val description: String,
    val unitPrice: Long,
    val quantity: Int
)

internal fun CreateInvoiceModel.toDataModel(): CreateInvoiceRequest = CreateInvoiceRequest(
    issueDate = this.issueDate,
    dueDate = this.dueDate,
    activities = this.activities.map {
        CreateInvoiceActivityRequest(
            description = it.description,
            unitPrice = it.unitPrice,
            quantity = it.quantity
        )
    },
    invoiceNumber = this.invoiceNumber,
    companyId = this.companyId,
    customerId = this.customerId
)
