package io.github.monolithic.invoicer.features.invoice.data.repository

import io.github.monolithic.invoicer.features.invoice.data.datasource.InvoiceDataSource
import io.github.monolithic.invoicer.features.invoice.data.model.toDataModel
import io.github.monolithic.invoicer.features.invoice.data.model.toDomainModel
import io.github.monolithic.invoicer.features.invoice.data.model.toModel
import io.github.monolithic.invoicer.features.invoice.domain.model.CreateInvoiceModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceDetailsModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceList
import io.github.monolithic.invoicer.features.invoice.domain.repository.InvoiceRepository

internal class InvoiceRepositoryImpl(
    private val dataSource: InvoiceDataSource
) : InvoiceRepository {

    override suspend fun getInvoices(
        page: Long,
        limit: Int,
        companyId: String,
        minIssueDate: String?,
        maxIssueDate: String?,
        minDueDate: String?,
        maxDueDate: String?,
        customerId: String?
    ): InvoiceList {
        return dataSource.getInvoices(
            page = page,
            limit = limit,
            minIssueDate = minIssueDate,
            maxIssueDate = maxIssueDate,
            minDueDate = minDueDate,
            maxDueDate = maxDueDate,
            customerId = customerId,
            companyId = companyId
        ).toDomainModel()
    }

    override suspend fun createInvoice(payload: CreateInvoiceModel) {
        return dataSource.createInvoice(
            payload = payload.toDataModel()
        )
    }

    override suspend fun getInvoiceDetails(
        invoiceId: String,
        companyId: String
    ): InvoiceDetailsModel {
        return dataSource.getInvoiceDetails(
            invoiceId = invoiceId,
            companyId = companyId
        ).toModel()
    }
}
