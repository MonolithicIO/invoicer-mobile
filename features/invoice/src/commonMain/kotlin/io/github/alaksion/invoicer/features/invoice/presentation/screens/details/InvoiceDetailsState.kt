package io.github.alaksion.invoicer.features.invoice.presentation.screens.details

import io.github.alaksion.invoicer.features.invoice.domain.model.InvoiceDetailsActivityModel
import io.github.alaksion.invoicer.features.invoice.presentation.model.InvoicePayAccountUiModel
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

internal data class InvoiceDetailsState(
    val invoiceNumber: String = "",
    val companyName: String = "",
    val companyAddress: String = "",
    val customerName: String = "",
    val issueDate: LocalDate = LocalDate(1970, 1, 1),
    val dueDate: LocalDate = LocalDate(1970, 1, 1),
    val primaryAccount: InvoicePayAccountUiModel = InvoicePayAccountUiModel(
        swift = "",
        iban = "",
        bankName = "",
        bankAddress = ""
    ),
    val intermediaryAccount: InvoicePayAccountUiModel? = null,
    val activities: List<InvoiceDetailsActivityModel> = listOf(),
    val createdAt: Instant = Instant.DISTANT_PAST,
    val mode: InvoiceDetailsMode = InvoiceDetailsMode.Content,
)

internal sealed interface InvoiceDetailsMode {
    data object Content : InvoiceDetailsMode
    data object Loading : InvoiceDetailsMode
    data class Error(
        val errorType: InvoiceDetailsErrorType
    ) : InvoiceDetailsMode
}

internal enum class InvoiceDetailsErrorType {
    NotFound,
    Generic
}
