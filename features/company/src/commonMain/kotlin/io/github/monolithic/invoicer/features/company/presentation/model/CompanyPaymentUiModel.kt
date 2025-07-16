package io.github.monolithic.invoicer.features.company.presentation.model

import io.github.monolithic.invoicer.features.company.domain.model.CompanyDetailsPaymentModel

internal data class CompanyPaymentUiModel(
    val iban: String,
    val swift: String,
    val bankName: String,
    val bankAddress: String,
    val id: String,
) {
    companion object {
        val Empty = CompanyPaymentUiModel(
            iban = "",
            swift = "",
            bankName = "",
            bankAddress = "",
            id = "",
        )
    }
}

internal fun CompanyDetailsPaymentModel.toUiModel(): CompanyPaymentUiModel {
    return CompanyPaymentUiModel(
        iban = iban,
        swift = swift,
        bankName = bankName,
        bankAddress = bankAddress,
        id = id,
    )
}
