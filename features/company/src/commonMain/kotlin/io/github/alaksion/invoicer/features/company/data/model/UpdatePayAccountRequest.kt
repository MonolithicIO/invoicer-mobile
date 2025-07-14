package io.github.alaksion.invoicer.features.company.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdatePayAccountRequest(
    val bankName: String,
    val bankAddress: String,
    val swift: String,
    val iban: String
)
