package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import io.github.alaksion.invoicer.foundation.utils.date.defaultFormat
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal data class InvoiceConfigurationState(
    val invoiceNumber: String = "",
    val invoiceDueDate: Long = 0L,
    val invoiceIssueDate: Long = 0L,
    val today: Long = 0L,
) {
    val isIssueDateValid = invoiceIssueDate.let { issueDate ->
        today.let { currentDate ->
            issueDate >= currentDate
        }
    }

    val isDueDateValid = invoiceDueDate.let { dueDate ->
        invoiceIssueDate.let { issueDate ->
            dueDate >= issueDate
        }
    }

    val isButtonEnabled = invoiceNumber.isNotBlank() && isDueDateValid && isIssueDateValid

    val dueDateFormatted = Instant
        .fromEpochMilliseconds(invoiceDueDate)
        .toLocalDateTime(TimeZone.UTC)
        .date
        .defaultFormat()

    val issueDateFormatted = Instant
        .fromEpochMilliseconds(invoiceIssueDate)
        .toLocalDateTime(TimeZone.UTC)
        .date
        .defaultFormat()
}
