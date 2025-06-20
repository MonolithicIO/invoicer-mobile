package io.github.alaksion.invoicer.features.customer.data.repository

import io.github.alaksion.invoicer.features.customer.data.model.CreateCustomerRequest
import io.github.alaksion.invoicer.features.customer.data.model.CustomerListResponse
import io.github.alaksion.invoicer.features.customer.domain.model.CreateCustomerModel
import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListItemModel
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
            val response = httpWrapper.client.get(
                "/v1/company/$companyId/customers"
            ) {
                parameter("page", page)
                parameter("limit", pageSize)
            }.body<CustomerListResponse>()

            CustomerListModel(
                itemCount = response.itemCount,
                nextPage = response.nextPage,
                items = response.items.map { item ->
                    CustomerListItemModel(
                        id = item.id,
                        name = item.name,
                        email = item.email,
                        phone = item.phone,
                        companyId = item.companyId,
                        createdAt = item.createdAt,
                        updatedAt = item.updatedAt
                    )
                }
            )
        }
    }
}
