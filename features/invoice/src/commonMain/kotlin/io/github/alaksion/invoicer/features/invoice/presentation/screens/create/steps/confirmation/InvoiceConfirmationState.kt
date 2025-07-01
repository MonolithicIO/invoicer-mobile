package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.activities.model.CreateInvoiceActivityUiModel
import io.github.alaksion.invoicer.foundation.utils.money.moneyFormat
import kotlinx.datetime.LocalDate

internal data class InvoiceConfirmationState(
    val companyName: String = "",
    val customerName: String = "",
    val issueDate: LocalDate = LocalDate(1, 1, 1),
    val dueDate: LocalDate = LocalDate(1, 1, 1),
    val invoiceNumber: String = "",
    val activities: List<CreateInvoiceActivityUiModel> = listOf(),
    val isLoading: Boolean = false
) {
    val totalAmount = activities
        .map { it.unitPrice * it.quantity }
        .ifEmpty { listOf(0L) }
        .reduce { acc, amount -> acc + amount }
        .moneyFormat()
}

internal sealed interface InvoiceConfirmationEvent {
    data object Success : InvoiceConfirmationEvent
    data class Error(val message: String) : InvoiceConfirmationEvent
}
