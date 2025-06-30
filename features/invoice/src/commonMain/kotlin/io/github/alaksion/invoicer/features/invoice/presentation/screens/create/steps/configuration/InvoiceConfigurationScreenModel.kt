package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.alaksion.invoicer.foundation.utils.date.AppTimeZone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.LocalDate
import kotlinx.datetime.plus
import kotlinx.datetime.todayIn

internal class InvoiceConfigurationScreenModel(
    private val invoiceForm: CreateInvoiceForm,
    private val clock: Clock
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfigurationState())
    val state = _state.asStateFlow()

    fun refreshState() {
        val issueDate = invoiceForm.issueDate ?: clock.todayIn(AppTimeZone)
        val dueDate = invoiceForm.dueDate ?: clock.todayIn(AppTimeZone).plus(DatePeriod(days = 7))

        _state.update {
            InvoiceConfigurationState(
                invoiceNumber = invoiceForm.invoiceNumber,
                invoiceDueDate = dueDate,
                invoiceIssueDate = issueDate
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