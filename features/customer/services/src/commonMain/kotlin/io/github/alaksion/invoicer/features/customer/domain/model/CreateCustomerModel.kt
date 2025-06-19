package io.github.alaksion.invoicer.features.customer.domain.model

internal data class CreateCustomerModel(
    val name: String,
    val email: String,
    val phone: String?,
    val companyId: String,
)
