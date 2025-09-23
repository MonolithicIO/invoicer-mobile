package io.github.monolithic.invoicer.features.company.services.data.datasource

import io.github.monolithic.invoicer.features.company.services.data.model.CompanyDetailsResponse
import io.github.monolithic.invoicer.features.company.services.data.model.CreateCompanyRequest
import io.github.monolithic.invoicer.features.company.services.data.model.ListCompaniesResponse
import io.github.monolithic.invoicer.features.company.services.data.model.UpdateAddressRequest
import io.ktor.client.HttpClient
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
    private val httpWrapper: HttpClient
) : CompanyRemoteDataSource {

    override suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesResponse {
        return withContext(dispatcher) {
            httpWrapper.get("/v1/company") {
                parameters {
                    append("page", page.toString())
                    append("limit", limit.toString())
                }
            }.body()
        }
    }

    override suspend fun createCompany(data: CreateCompanyRequest): String {
        return withContext(dispatcher) {
            httpWrapper.post("/v1/company") {
                setBody(data)
            }.body<String>()
        }
    }

    override suspend fun details(companyId: String): CompanyDetailsResponse {
        return withContext(dispatcher) {
            httpWrapper.get("/v1/company/$companyId").body()
        }
    }

    override suspend fun updateAddres(companyId: String, request: UpdateAddressRequest) {
        return withContext(dispatcher) {
            httpWrapper.patch("v1/company/$companyId/address") {
                setBody(request)
            }
        }
    }
}
