package io.github.monolithic.invoicer.features.company.services.domain.model

data class UpdateAddressModel(
    val addressLine: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
)
