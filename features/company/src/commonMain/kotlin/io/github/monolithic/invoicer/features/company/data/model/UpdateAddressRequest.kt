package io.github.monolithic.invoicer.features.company.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateAddressRequest(
    val addressLine: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null
)
