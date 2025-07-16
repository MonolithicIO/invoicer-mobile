package io.github.monolithic.invoicer.features.company.domain.model

internal data class UpdatePayAccountModel(
    val bankName: String,
    val bankAddress: String,
    val swift: String,
    val iban: String
)
