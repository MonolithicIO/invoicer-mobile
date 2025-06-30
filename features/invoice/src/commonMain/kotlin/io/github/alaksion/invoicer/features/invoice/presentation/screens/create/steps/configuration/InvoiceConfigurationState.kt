package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import kotlinx.datetime.LocalDate

internal data class InvoiceConfigurationState(
    val invoiceNumber: String = "",
    val invoiceDueDate: LocalDate = LocalDate(0, 1, 1),
    val invoiceIssueDate: LocalDate = LocalDate(0, 1, 1),
    private val today: LocalDate? = null,
) {
    val isIssueDateValid = invoiceIssueDate.let { issueDate ->
        today?.let { currentDate ->
            issueDate >= currentDate
        } ?: false
    }

    val isDueDateValid = invoiceDueDate.let { dueDate ->
        invoiceIssueDate.let { issueDate ->
            dueDate >= issueDate
        }
    }

    val isButtonEnabled = invoiceNumber.isNotBlank() && isDueDateValid && isIssueDateValid
}
