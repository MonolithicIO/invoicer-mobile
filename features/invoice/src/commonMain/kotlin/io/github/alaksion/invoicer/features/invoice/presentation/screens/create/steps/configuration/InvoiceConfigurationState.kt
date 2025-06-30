package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import io.github.alaksion.invoicer.foundation.utils.date.AppTimeZone
import io.github.alaksion.invoicer.foundation.utils.date.defaultFormat
import kotlinx.datetime.Instant
import kotlinx.datetime.toLocalDateTime

internal data class InvoiceConfigurationState(
    val invoiceNumber: String = "",
    val invoiceDueDate: Long = 0L,
    val invoiceIssueDate: Long = 0L,
    private val today: Long? = null,
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

    val dueDateFormatted = Instant
        .fromEpochMilliseconds(invoiceDueDate)
        .toLocalDateTime(AppTimeZone)
        .date
        .defaultFormat()

    val issueDateFormatted = Instant
        .fromEpochMilliseconds(invoiceIssueDate)
        .toLocalDateTime(AppTimeZone)
        .date
        .defaultFormat()
}
