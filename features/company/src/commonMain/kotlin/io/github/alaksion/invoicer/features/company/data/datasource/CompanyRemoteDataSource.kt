package io.github.alaksion.invoicer.features.company.data.datasource

import io.github.alaksion.invoicer.features.company.data.model.CreateCompanyRequest
import io.github.alaksion.invoicer.features.company.data.model.ListCompaniesResponse
import io.github.alaksion.invoicer.foundation.network.client.HttpWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.parameters
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface CompanyRemoteDataSource {
    suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesResponse

    suspend fun createCompany(
        data: CreateCompanyRequest
    ): String
}

internal class CompanyRemoteDataSourceImpl(
    private val dispatcher: CoroutineDispatcher,
    private val httpWrapper: HttpWrapper
) : CompanyRemoteDataSource {

    override suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesResponse {
        return withContext(dispatcher) {
            httpWrapper.client.get("/v1/company") {
                parameters {
                    append("page", page.toString())
                    append("limit", limit.toString())
                }
            }.body()
        }
    }

    override suspend fun createCompany(data: CreateCompanyRequest): String {
        return withContext(dispatcher) {
            httpWrapper.client.post("/v1/company") {
                setBody(data)
            }.body<String>()
        }
    }
}
