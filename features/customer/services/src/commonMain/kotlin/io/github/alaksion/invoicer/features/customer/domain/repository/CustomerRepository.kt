package io.github.alaksion.invoicer.features.customer.domain.repository

import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListModel
import io.github.alaksion.invoicer.features.customer.domain.model.CreateCustomerModel

internal interface CustomerRepository {

    suspend fun createCustomer(
        model: CreateCustomerModel
    ): String

    suspend fun listCustomers(
        companyId: String,
        page: Long = 0L,
        pageSize: Long = 20L
    ): CustomerListModel
}