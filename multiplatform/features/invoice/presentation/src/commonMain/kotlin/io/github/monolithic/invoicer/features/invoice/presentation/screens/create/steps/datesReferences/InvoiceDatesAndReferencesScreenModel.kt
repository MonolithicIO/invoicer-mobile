package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.datesReferences

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.invoice.presentation.ext.toDateInputString
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

internal class InvoiceDatesAndReferencesScreenModel(
    private val invoiceForm: CreateInvoiceForm,
    private val clock: Clock
) : ScreenModel {

    private val today by lazy {
        clock.now().toLocalDateTime(TimeZone.currentSystemDefault()).date
    }

    private val _state = MutableStateFlow(InvoiceDatesAndReferencesState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<InvoiceDatesAndReferencesUiEvents>()
    val events = _events.asSharedFlow()

    fun refreshState() {
        _state.update {
            it.copy(
                invoiceId = invoiceForm.invoiceNumber,
                issueDate = invoiceForm.issueDate.toDateInputString(),
                dueDate = invoiceForm.dueDate.toDateInputString()
            )
        }
    }

    fun updateInvoiceNumber(invoiceNumber: String) {
        _state.update {
            it.copy(invoiceId = invoiceNumber.trim())
        }
    }

    fun updateInvoiceDueDate(
        invoiceDueDate: String
    ) {
        _state.update { it.copy(dueDate = invoiceDueDate.trim()) }
    }

    fun updateIssueDate(
        issueDate: String
    ) {
        _state.update { it.copy(issueDate = issueDate.trim()) }
    }

    private fun validateIssueDate(): DateState {
        val parsedIssueDate = state.value.parsedIssueDate
        val notNull = parsedIssueDate != null

        return when {
            parsedIssueDate == null -> DateState.Invalid
            notNull && parsedIssueDate < today -> DateState.Past
            else -> DateState.Valid
        }
    }

    private fun validateDueDate(): DateState {
        val parsedDueDate = state.value.parsedDueDate
        val notNull = parsedDueDate != null
        val parsedIssueDate = state.value.parsedIssueDate

        val beforeIssueDate = if (parsedIssueDate == null) {
            false
        } else {
            notNull && parsedDueDate < parsedIssueDate
        }


        return when {
            parsedDueDate == null -> DateState.Invalid
            notNull && parsedDueDate < today -> DateState.Past
            beforeIssueDate -> DateState.DueDateBeforeIssue
            else -> DateState.Valid
        }
    }

    fun saveConfiguration() {
        if (_state.value.isButtonEnabled) {
            val issueDateValid = validateIssueDate()
            val dueDateValid = validateDueDate()

            _state.update { oldState ->
                oldState.copy(
                    issueDateState = issueDateValid,
                    dueDateState = dueDateValid
                )
            }

            val datesValid = issueDateValid == DateState.Valid && dueDateValid == DateState.Valid

            if (datesValid) {
                invoiceForm.invoiceNumber = state.value.invoiceId
                state.value.parsedDueDate?.let {
                    invoiceForm.dueDate = it
                }
                state.value.parsedIssueDate?.let {
                    invoiceForm.issueDate = it
                }
                screenModelScope.launch {
                    _events.emit(InvoiceDatesAndReferencesUiEvents.Continue)
                }
            }
        }
    }
}
