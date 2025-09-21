package io.github.monolithic.invoicer.features.company.data.datasource

import io.github.monolithic.invoicer.features.company.data.model.UpdatePayAccountRequest
import io.ktor.client.HttpClient
import io.ktor.client.request.delete
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal interface PayAccountRemoteDataSource {
    suspend fun updatePayAccount(
        request: UpdatePayAccountRequest,
        companyId: String,
        payAccountId: String
    )

    suspend fun deletePayAccount(
        companyId: String,
        payAccountId: String
    )
}

internal class PayAccountRemoteDataSourceImpl(
    private val httpWrapper: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : PayAccountRemoteDataSource {

    override suspend fun updatePayAccount(
        request: UpdatePayAccountRequest,
        companyId: String,
        payAccountId: String
    ) {
        withContext(dispatcher) {
            httpWrapper.put("/v1/company/$companyId/pay_account/$payAccountId") {
                setBody(request)
            }
        }
    }

    override suspend fun deletePayAccount(companyId: String, payAccountId: String) {
        withContext(dispatcher) {
            httpWrapper.delete("/v1/company/$companyId/pay_account/$payAccountId")
        }
    }
}
