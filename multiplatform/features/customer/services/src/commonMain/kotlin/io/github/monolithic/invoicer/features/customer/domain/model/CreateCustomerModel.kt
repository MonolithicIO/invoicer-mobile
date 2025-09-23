package io.github.monolithic.invoicer.features.customer.domain.model

data class CreateCustomerModel(
    val name: String,
    val email: String,
    val phone: String?,
    val companyId: String,
)
