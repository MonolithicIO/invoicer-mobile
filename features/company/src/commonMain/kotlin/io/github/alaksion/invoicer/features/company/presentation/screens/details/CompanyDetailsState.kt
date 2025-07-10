package io.github.alaksion.invoicer.features.company.presentation.screens.details

import io.github.alaksion.invoicer.features.company.presentation.model.CompanyPaymentUiModel

internal data class CompanyDetailsState(
    val name: String = "",
    val document: String = "",
    val addressLine1: String = "",
    val addressLine2: String? = null,
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val countryCode: String = "",
    val payAccount: CompanyPaymentUiModel = CompanyPaymentUiModel.Empty,
    val intermediaryAccount: CompanyPaymentUiModel? = null,
    val mode: CompanyDetailsMode = CompanyDetailsMode.Loading,
)

internal enum class CompanyDetailsMode {
    Loading,
    Content,
    Error;
}