package io.github.monolithic.features.home.domain.services

import io.github.monolithic.features.home.domain.model.HomeDetailsModel
import io.github.monolithic.invoicer.features.invoice.services.domain.repository.InvoiceRepository
import io.github.monolithic.invoicer.foundation.auth.session.Session

internal interface GetHomeDetailsService {
    suspend fun get(): HomeDetailsModel
}

internal class GetHomeDetailsServiceImpl(
    private val invoiceRepository: InvoiceRepository,
    private val session: Session
) : GetHomeDetailsService {

    override suspend fun get(): HomeDetailsModel {
        val top3Invoices = invoiceRepository.getInvoices(
            page = 0,
            limit = 3,
            companyId = session.getCompany().id,
            minIssueDate = null,
            maxIssueDate = null,
            minDueDate = null,
            maxDueDate = null,
            customerId = null
        )

        return HomeDetailsModel(
            top3Invoices = top3Invoices.items.take(InvoiceCap)
        )
    }

    companion object {
        private const val InvoiceCap = 3
    }
}
