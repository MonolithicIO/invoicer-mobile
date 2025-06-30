package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate

internal class InvoiceConfigurationScreenModel(
    private val invoiceForm: CreateInvoiceForm,
    private val clock: Clock
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfigurationState())
    val state = _state.asStateFlow()

    fun refreshState() {
        _state.update {
            InvoiceConfigurationState(
                invoiceNumber = invoiceForm.invoiceNumber,
                invoiceDueDate = invoiceForm.dueDate,
                invoiceIssueDate = invoiceForm.issueDate
            )
        }
    }

    fun updateInvoiceNumber(invoiceNumber: String) {
        _state.update {
            it.copy(invoiceNumber = invoiceNumber)
        }
    }

    fun updateInvoiceDueDate(
        invoiceDueDate: LocalDate
    ) {
        _state.update {
            it.copy(invoiceDueDate = invoiceDueDate)
        }
    }

    fun updateIssueDate(
        issueDate: LocalDate
    ) {
        _state.update {
            it.copy(invoiceIssueDate = issueDate)
        }
    }

    fun saveConfiguration() {
        invoiceForm.invoiceNumber = state.value.invoiceNumber
        invoiceForm.dueDate = state.value.invoiceDueDate
        invoiceForm.issueDate = state.value.invoiceIssueDate
    }
}