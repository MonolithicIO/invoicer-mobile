package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.configuration

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

internal class InvoiceConfigurationScreenModel(
    private val invoiceForm: CreateInvoiceForm,
    private val clock: Clock
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfigurationState())
    val state = _state.asStateFlow()

    fun refreshState() {
        val today = clock.now()

        val issueDate =
            if (invoiceForm.issueDate == 0L) today.toEpochMilliseconds()
            else invoiceForm.issueDate

        val dueDate =
            if (invoiceForm.dueDate == 0L) today.plus(7.days).toEpochMilliseconds()
            else invoiceForm.dueDate

        _state.update {
            InvoiceConfigurationState(
                invoiceNumber = invoiceForm.invoiceNumber,
                invoiceDueDate = dueDate,
                invoiceIssueDate = issueDate,
                today = today.toEpochMilliseconds()
            )
        }
    }

    fun updateInvoiceNumber(invoiceNumber: String) {
        _state.update {
            it.copy(invoiceNumber = invoiceNumber)
        }
    }

    fun updateInvoiceDueDate(
        invoiceDueDate: Long
    ) {
        _state.update { it.copy(invoiceDueDate = invoiceDueDate) }
    }

    fun updateIssueDate(
        issueDate: Long
    ) {
        _state.update { it.copy(invoiceIssueDate = issueDate) }
    }

    fun saveConfiguration() {
        invoiceForm.invoiceNumber = state.value.invoiceNumber
        invoiceForm.dueDate = state.value.invoiceDueDate
        invoiceForm.issueDate = state.value.invoiceIssueDate
    }
}
