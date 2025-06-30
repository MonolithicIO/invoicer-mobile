package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.LocalDateTime

internal class InvoiceConfigurationScreenModel(
    private val invoiceForm: CreateInvoiceForm,
) {

    private val _state = MutableStateFlow(InvoiceConfigurationState())
    val state = _state.asStateFlow()

    fun updateInvoiceNumber(invoiceNumber: String) {
        _state.update {
            it.copy(invoiceNumber = invoiceNumber)
        }
    }

    fun updateInvoiceDueDate(
        invoiceDueDate: LocalDateTime
    ) {
        _state.update {
            it.copy(invoiceDueDate = invoiceDueDate)
        }
    }

    fun updateIssueDate(
        issueDate: LocalDateTime
    ) {
        _state.update {
            it.copy(invoiceIssueDate = issueDate)
        }
    }
}