package io.github.monolithic.invoicer.features.company.services.domain.model

data class UpdatePayAccountModel(
    val bankName: String,
    val bankAddress: String,
    val swift: String,
    val iban: String
)
