package io.github.alaksion.invoicer.features.customer.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateCustomerRequest(
    val name: String,
    val email: String,
    val phone: String?,
)
