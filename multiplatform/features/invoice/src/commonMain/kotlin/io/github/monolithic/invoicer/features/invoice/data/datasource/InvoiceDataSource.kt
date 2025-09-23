package io.github.monolithic.invoicer.features.invoice.data.datasource

import io.github.monolithic.invoicer.features.invoice.data.model.CreateInvoiceRequest
import io.github.monolithic.invoicer.features.invoice.data.model.InvoiceDetailsResponse
import io.github.monolithic.invoicer.features.invoice.data.model.InvoiceListResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface InvoiceDataSource {
    suspend fun getInvoices(
        page: Long,
        limit: Int,
        companyId: String,
        minIssueDate: String?,
        maxIssueDate: String?,
        minDueDate: String?,
        maxDueDate: String?,
        customerId: String?
    ): InvoiceListResponse

    suspend fun createInvoice(
        payload: CreateInvoiceRequest
    )

    suspend fun getInvoiceDetails(
        invoiceId: String,
        companyId: String,
    ): InvoiceDetailsResponse
}

internal class InvoiceDataSourceImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : InvoiceDataSource {

    override suspend fun getInvoices(
        page: Long,
        limit: Int,
        companyId: String,
        minIssueDate: String?,
        maxIssueDate: String?,
        minDueDate: String?,
        maxDueDate: String?,
        customerId: String?
    ): InvoiceListResponse {
        return withContext(dispatcher) {
            httpClient.get(urlString = "/v1/company/$companyId/invoices") {
                parameters {
                    append("page", page.toString())
                    append("limit", limit.toString())
                    minIssueDate?.let { append("minIssueDate", it) }
                    maxIssueDate?.let { append("maxIssueDate", it) }
                    minDueDate?.let { append("minDueDate", it) }
                    maxDueDate?.let { append("maxDueDate", it) }
                    customerId?.let { append("customerId", it) }
                }
            }.body()
        }
    }

    override suspend fun createInvoice(
        payload: CreateInvoiceRequest
    ) {
        withContext(dispatcher) {
            httpClient.post(
                urlString = "/v1/company/${payload.companyId}/invoice"
            ) {
                setBody(payload)
            }
        }
    }

    override suspend fun getInvoiceDetails(
        invoiceId: String,
        companyId: String,
    ): InvoiceDetailsResponse {
        return withContext(dispatcher) {
            httpClient.get(urlString = "/v1/company/$companyId/invoice/$invoiceId").body()
        }
    }
}
