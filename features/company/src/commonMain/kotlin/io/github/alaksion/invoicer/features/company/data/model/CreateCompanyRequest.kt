package io.github.alaksion.invoicer.features.company.data.model

import kotlinx.serialization.Serializable

@Serializable
internal data class CreateCompanyRequest(
    val name: String? = null,
    val document: String? = null,
    val address: CreateCompanyAddressRequest? = null,
    val paymentAccount: CreateCompanyPaymentAccountRequest? = null,
    val intermediaryAccount: CreateCompanyPaymentAccountRequest? = null,
)

@Serializable
internal data class CreateCompanyAddressRequest(
    val addressLine1: String? = null,
    val addressLine2: String? = null,
    val city: String? = null,
    val state: String? = null,
    val postalCode: String? = null,
    val countryCode: String? = null,
)

@Serializable
internal data class CreateCompanyPaymentAccountRequest(
    val iban: String? = null,
    val swift: String? = null,
    val bankName: String? = null,
    val bankAddress: String? = null,
)
