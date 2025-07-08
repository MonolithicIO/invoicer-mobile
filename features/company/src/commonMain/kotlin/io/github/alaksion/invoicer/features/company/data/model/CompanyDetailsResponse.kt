package io.github.alaksion.invoicer.features.company.data.model

import kotlinx.datetime.Instant
import kotlinx.serialization.Serializable

@Serializable
internal data class CompanyDetailsResponse(
    val name: String,
    val document: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isDeleted: Boolean,
    val userId: String,
    val id: String,
    val address: CompanyDetailsAddressResponse,
    val paymentAccount: CompanyDetailsPaymentResponse,
    val intermediaryAccount: CompanyDetailsPaymentResponse?,
    val user: CompanyDetailsUserResponse
)

@Serializable
internal data class CompanyDetailsAddressResponse(
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
    val countryCode: String,
)

@Serializable
internal data class CompanyDetailsUserResponse(
    val id: String,
    val email: String,
)

@Serializable
internal data class CompanyDetailsPaymentResponse(
    val iban: String,
    val swift: String,
    val bankName: String,
    val bankAddress: String,
    val type: String,
    val isDeleted: Boolean,
    val createdAt: Instant,
    val updatedAt: Instant,
    val id: String,
)