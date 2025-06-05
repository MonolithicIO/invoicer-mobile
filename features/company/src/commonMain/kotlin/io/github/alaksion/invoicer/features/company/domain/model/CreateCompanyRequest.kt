package io.github.alaksion.invoicer.features.company.domain.model

internal data class CreateCompanyModel(
    val name: String,
    val document: String,
    val address: CreateCompanyAddressModel,
    val paymentAccount: CreateCompanyPaymentAccountModel,
    val intermediaryAccount: CreateCompanyPaymentAccountModel?,
)

internal data class CreateCompanyAddressModel(
    val addressLine1: String,
    val addressLine2: String,
    val city: String,
    val state: String,
    val postalCode: String,
    val countryCode: String,
)

internal data class CreateCompanyPaymentAccountModel(
    val iban: String,
    val swift: String,
    val bankName: String,
    val bankAddress: String,
)
