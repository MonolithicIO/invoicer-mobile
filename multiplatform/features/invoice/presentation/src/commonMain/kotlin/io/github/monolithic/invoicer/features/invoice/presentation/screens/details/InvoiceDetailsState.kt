package io.github.monolithic.invoicer.features.invoice.presentation.screens.details

import io.github.monolithic.invoicer.features.invoice.presentation.model.InvoicePayAccountUiModel
import io.github.monolithic.invoicer.features.invoice.services.domain.model.InvoiceDetailsActivityModel
import io.github.monolithic.invoicer.foundation.utils.date.Default
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate

internal data class InvoiceDetailsState(
    val invoiceNumber: String = "",
    val companyName: String = "",
    val companyAddress: String = "",
    val customerName: String = "",
    val issueDate: LocalDate = LocalDate.Default,
    val dueDate: LocalDate = LocalDate.Default,
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
) {
    val invoiceTotal = activities
        .map { it.quantity * it.unitPrice }
        .ifEmpty { listOf(0L) }
        .reduce { acc, value -> acc + value }
}

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
