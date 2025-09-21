package io.github.monolithic.invoicer.features.company.services.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateCompanyRequest(
    val name: String,
    val document: String,
    val address: CreateCompanyAddressRequest,
    val paymentAccount: CreateCompanyPaymentAccountRequest,
    val intermediaryAccount: CreateCompanyPaymentAccountRequest?,
)

@Serializable
internal data class CreateCompanyAddressRequest(
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
    val countryCode: String,
)

@Serializable
internal data class CreateCompanyPaymentAccountRequest(
    val iban: String,
    val swift: String,
    val bankName: String,
    val bankAddress: String,
)
