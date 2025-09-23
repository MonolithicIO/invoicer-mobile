package io.github.monolithic.invoicer.features.invoice.domain.repository

import io.github.monolithic.invoicer.features.invoice.domain.model.CreateInvoiceModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceDetailsModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceList

internal interface InvoiceRepository {
    suspend fun getInvoices(
        page: Long,
        limit: Int,
        companyId: String,
        minIssueDate: String?,
        maxIssueDate: String?,
        minDueDate: String?,
        maxDueDate: String?,
        customerId: String?
    ): InvoiceList

    suspend fun createInvoice(
        payload: CreateInvoiceModel
    )

    suspend fun getInvoiceDetails(
        invoiceId: String,
        companyId: String
    ): InvoiceDetailsModel
}
