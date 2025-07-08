package io.github.alaksion.invoicer.features.company.domain.model

import kotlinx.datetime.Instant

internal data class CompanyDetailsModel(
    val name: String,
    val document: String,
    val createdAt: Instant,
    val updatedAt: Instant,
    val isDeleted: Boolean,
    val userId: String,
    val id: String,
    val address: CompanyDetailsAddressModel,
    val paymentAccount: CompanyDetailsPaymentModel,
    val intermediaryAccount: CompanyDetailsPaymentModel?,
)

internal data class CompanyDetailsAddressModel(
    val addressLine1: String,
    val addressLine2: String?,
    val city: String,
    val state: String,
    val postalCode: String,
    val countryCode: String,
)

internal data class CompanyDetailsPaymentModel(
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