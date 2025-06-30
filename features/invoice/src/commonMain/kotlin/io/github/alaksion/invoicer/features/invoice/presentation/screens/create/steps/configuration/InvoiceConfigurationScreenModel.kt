package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.configuration

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.alaksion.invoicer.foundation.utils.date.AppTimeZone
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Duration.Companion.days

internal class InvoiceConfigurationScreenModel(
    private val invoiceForm: CreateInvoiceForm,
    private val clock: Clock
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfigurationState())
    val state = _state.asStateFlow()

    fun refreshState() {
        val issueDate = if (invoiceForm.dueDate == 0L) {
            clock.now().toEpochMilliseconds()
        } else {
            invoiceForm.dueDate
        }

        val dueDate = if (invoiceForm.issueDate == 0L) {
            clock.now().plus(7.days).toEpochMilliseconds()
        } else {
            invoiceForm.issueDate
        }

        _state.update {
            InvoiceConfigurationState(
                invoiceNumber = invoiceForm.invoiceNumber,
                invoiceDueDate = dueDate,
                invoiceIssueDate = issueDate,
                today = clock.now().toEpochMilliseconds()
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
        val correctedMillis = Instant
            .fromEpochMilliseconds(invoiceDueDate)
            .toLocalDateTime(AppTimeZone)
            .toInstant(AppTimeZone)
            .toEpochMilliseconds()

        _state.update {
            it.copy(
                invoiceDueDate = correctedMillis,
            )
        }
    }

    fun updateIssueDate(
        issueDate: Long
    ) {
        val correctedMillis = Instant
            .fromEpochMilliseconds(issueDate)
            .toLocalDateTime(AppTimeZone)
            .toInstant(AppTimeZone)
            .toEpochMilliseconds()

        _state.update {
            it.copy(
                invoiceIssueDate = correctedMillis
            )
        }
    }

    fun saveConfiguration() {
        invoiceForm.invoiceNumber = state.value.invoiceNumber
        invoiceForm.dueDate = state.value.invoiceDueDate
        invoiceForm.issueDate = state.value.invoiceIssueDate
    }
}