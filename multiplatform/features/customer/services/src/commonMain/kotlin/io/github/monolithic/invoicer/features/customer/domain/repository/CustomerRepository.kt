package io.github.monolithic.invoicer.features.customer.domain.repository

import io.github.monolithic.invoicer.features.customer.domain.model.CreateCustomerModel
import io.github.monolithic.invoicer.features.customer.domain.model.CustomerListModel

interface CustomerRepository {

    suspend fun createCustomer(
        model: CreateCustomerModel
    ): String

    suspend fun listCustomers(
        companyId: String,
        page: Long = 0L,
        pageSize: Long = 20L
    ): CustomerListModel
}
