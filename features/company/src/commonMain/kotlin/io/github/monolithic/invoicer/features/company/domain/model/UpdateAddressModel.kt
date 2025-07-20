package io.github.monolithic.invoicer.features.company.domain.model

internal data class UpdateAddressModel(
    val addressLine: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
)
