package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import kotlinx.datetime.LocalDate

internal data class InvoiceConfigurationState(
    val invoiceNumber: String = "",
    val invoiceDueDate: LocalDate? = null,
    val invoiceIssueDate: LocalDate? = null,
    private val today: LocalDate? = null
) {
    val isIssueDateValid = invoiceIssueDate?.let { issueDate ->
        today?.let { currentDate ->
            issueDate >= currentDate
        } ?: false
    } ?: false

    val isDueDateValid = invoiceDueDate?.let { dueDate ->
        invoiceIssueDate?.let { issueDate ->
            dueDate >= issueDate
        } ?: false
    } ?: false

    val isButtonEnabled = invoiceNumber.isNotBlank() && isDueDateValid && isIssueDateValid
}
