package io.github.monolithic.invoicer.features.invoice.presentation.fakes

import io.github.monolithic.invoicer.features.invoice.domain.model.CreateInvoiceModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceDetailsModel
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceList
import io.github.monolithic.invoicer.features.invoice.domain.model.InvoiceListItem
import io.github.monolithic.invoicer.features.invoice.domain.repository.InvoiceRepository
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

internal class FakeInvoiceRepository : InvoiceRepository {

    var getInvoicesFails = false

    override suspend fun getInvoices(
        page: Long,
        limit: Int,
        companyId: String,
        minIssueDate: String?,
        maxIssueDate: String?,
        minDueDate: String?,
        maxDueDate: String?,
        customerId: String?,
    ): InvoiceList {
        if (getInvoicesFails) error("Fake error")

        return InvoiceList(
            items = INVOICE_LIST,
            totalItemCount = INVOICE_LIST.size.toLong(),
            nextPage = null,
        )
    }

    override suspend fun createInvoice(payload: CreateInvoiceModel) = Unit

    override suspend fun getInvoiceDetails(
        invoiceId: String,
        companyId: String
    ): InvoiceDetailsModel {
        TODO("Not yet implemented")
    }

    companion object {
        val INVOICE_1 = InvoiceListItem(
            id = "123",
            externalId = "Invoice 1",
            issueDate = LocalDate.parse("2023-10-01"),
            dueDate = LocalDate.parse("2023-10-01"),
            createdAt = Instant.parse("2023-10-01T00:00:00Z"),
            updatedAt = Instant.parse("2023-10-01T00:00:00Z"),
            totalAmount = 1000,
            companyName = "company",
            customerName = "customer",
        )

        val INVOICE_2 = InvoiceListItem(
            id = "456",
            externalId = "Invoice 2",
            companyName = "company",
            customerName = "customer",
            issueDate = LocalDate.parse("2023-10-02"),
            dueDate = LocalDate.parse("2023-10-02"),
            createdAt = Instant.parse("2023-10-02T00:00:00Z"),
            updatedAt = Instant.parse("2023-10-02T00:00:00Z"),
            totalAmount = 2000
        )

        val INVOICE_LIST = listOf(
            INVOICE_1,
            INVOICE_2
        )
    }
}
