package io.github.alaksion.invoicer.features.invoice.presentation.model

import io.github.alaksion.invoicer.features.invoice.domain.model.InvoiceDetailsPayAccountModel

internal data class InvoicePayAccountUiModel(
    val swift: String,
    val iban: String,
    val bankName: String,
    val bankAddress: String,
)

internal fun InvoiceDetailsPayAccountModel.toUiModel(): InvoicePayAccountUiModel {
    return InvoicePayAccountUiModel(
        swift = swift,
        iban = iban,
        bankName = bankName,
        bankAddress = bankAddress
    )
}
