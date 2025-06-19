package io.github.alaksion.invoicer.features.customer.data.repository

import io.github.alaksion.invoicer.features.customer.data.model.CreateCustomerRequest
import io.github.alaksion.invoicer.features.customer.domain.model.CreateCustomerModel
import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListModel
import io.github.alaksion.invoicer.features.customer.domain.repository.CustomerRepository
import io.github.alaksion.invoicer.foundation.network.client.HttpWrapper
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class CustomerRepositoryImpl(
    private val httpWrapper: HttpWrapper,
    private val dispatcher: CoroutineDispatcher
) : CustomerRepository {

    override suspend fun createCustomer(model: CreateCustomerModel): String {
        return withContext(dispatcher) {
            httpWrapper.client.post(
                "/v1/company/${model.companyId}/customer"
            ) {
                setBody(
                    CreateCustomerRequest(
                        name = model.name,
                        email = model.email,
                        phone = model.phone
                    )
                )
            }
        }.body()
    }

    override suspend fun listCustomers(
        companyId: String,
        page: Long,
        pageSize: Long
    ): CustomerListModel {
        return withContext(dispatcher) {
            httpWrapper.client.get(
                "/v1/company/$companyId/customers"
            ) {
                parameter("page", page)
                parameter("limit", pageSize)
            }
        }.body()
    }
}