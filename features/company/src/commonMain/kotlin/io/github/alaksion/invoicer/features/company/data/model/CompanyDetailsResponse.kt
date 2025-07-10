package io.github.alaksion.invoicer.features.company.data.model

import io.github.alaksion.invoicer.features.company.domain.model.CompanyDetailsAddressModel
import io.github.alaksion.invoicer.features.company.domain.model.CompanyDetailsModel
import io.github.alaksion.invoicer.features.company.domain.model.CompanyDetailsPaymentModel
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

internal fun CompanyDetailsResponse.toModel(): CompanyDetailsModel = CompanyDetailsModel(
    name = name,
    document = document,
    createdAt = createdAt,
    updatedAt = updatedAt,
    isDeleted = isDeleted,
    userId = userId,
    id = id,
    address = CompanyDetailsAddressModel(
        addressLine1 = address.addressLine1,
        addressLine2 = address.addressLine2,
        city = address.city,
        state = address.state,
        postalCode = address.postalCode,
        countryCode = address.countryCode
    ),
    paymentAccount = CompanyDetailsPaymentModel(
        iban = paymentAccount.iban,
        swift = paymentAccount.swift,
        bankName = paymentAccount.bankName,
        bankAddress = paymentAccount.bankAddress,
        type = paymentAccount.type,
        isDeleted = paymentAccount.isDeleted,
        createdAt = paymentAccount.createdAt,
        updatedAt = paymentAccount.updatedAt,
        id = paymentAccount.id
    ),
    intermediaryAccount = intermediaryAccount?.let {
        CompanyDetailsPaymentModel(
            iban = it.iban,
            swift = it.swift,
            bankName = it.bankName,
            bankAddress = it.bankAddress,
            type = it.type,
            isDeleted = it.isDeleted,
            createdAt = it.createdAt,
            updatedAt = it.updatedAt,
            id = it.id
        )
    },
)
