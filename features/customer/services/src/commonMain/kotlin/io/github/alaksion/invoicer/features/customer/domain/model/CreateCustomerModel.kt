package io.github.alaksion.invoicer.features.customer.domain.model

data class CreateCustomerModel(
    val name: String,
    val email: String,
    val phone: String?,
    val companyId: String,
)
