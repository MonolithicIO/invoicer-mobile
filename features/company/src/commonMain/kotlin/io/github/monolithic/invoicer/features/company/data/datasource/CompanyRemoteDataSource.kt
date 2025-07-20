package io.github.monolithic.invoicer.features.company.data.datasource

import io.github.monolithic.invoicer.features.company.data.model.CompanyDetailsResponse
import io.github.monolithic.invoicer.features.company.data.model.CreateCompanyRequest
import io.github.monolithic.invoicer.features.company.data.model.ListCompaniesResponse
import io.github.monolithic.invoicer.features.company.data.model.UpdateAddressRequest
import io.github.monolithic.invoicer.foundation.network.client.HttpWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
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

    suspend fun details(
        companyId: String
    ): CompanyDetailsResponse

    suspend fun updateAddres(
        companyId: String,
        request: UpdateAddressRequest
    )
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

    override suspend fun details(companyId: String): CompanyDetailsResponse {
        return withContext(dispatcher) {
            httpWrapper.client.get("/v1/company/$companyId").body()
        }
    }

    override suspend fun updateAddres(companyId: String, request: UpdateAddressRequest) {
        return withContext(dispatcher) {
            httpWrapper.client.patch("v1/company/$companyId/address") {
                setBody(request)
            }
        }
    }
}
