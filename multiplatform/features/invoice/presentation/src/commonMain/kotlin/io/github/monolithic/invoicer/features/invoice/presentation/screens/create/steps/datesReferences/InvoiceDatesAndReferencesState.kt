package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.datesReferences

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_invalid_due_before_issue
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_invalid_format
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_invalid_past
import io.github.monolithic.invoicer.features.invoice.presentation.ext.toLocalDate
import org.jetbrains.compose.resources.stringResource

internal data class InvoiceDatesAndReferencesState(
    val invoiceId: String = "",
    val dueDate: String = "",
    val issueDate: String = "",
    val issueDateState: DateState = DateState.Valid,
    val dueDateState: DateState = DateState.Valid,
) {
    val isButtonEnabled = invoiceId.isNotBlank()
            && issueDate.isNotBlank()
            && dueDate.isNotBlank()

    val parsedIssueDate = issueDate.toLocalDate()
    val parsedDueDate = dueDate.toLocalDate()
}

internal enum class DateState {
    Valid,
    Invalid,
    Past,
    DueDateBeforeIssue;

    @Composable
    fun text(): String? = when (this) {
        Valid -> null
        Invalid -> stringResource(Res.string.invoice_date_invalid_format)
        Past -> stringResource(Res.string.invoice_date_invalid_past)
        DueDateBeforeIssue -> stringResource(Res.string.invoice_date_invalid_due_before_issue)
    }
}

internal enum class InvoiceDatesAndReferencesUiEvents {
    Continue;
}
