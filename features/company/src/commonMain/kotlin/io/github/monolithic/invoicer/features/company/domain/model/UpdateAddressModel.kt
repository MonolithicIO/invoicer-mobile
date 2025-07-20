package io.github.monolithic.invoicer.features.company.domain.model

internal data class UpdateAddressModel(
    val addressLine: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null
)
