package io.github.monolithic.invoicer.features.company.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateAddressRequest(
    val addressLine: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
)
