package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.utils.date.toLocalDate
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.update
import kotlinx.datetime.TimeZone

internal class InvoiceConfirmationScreenModel(
    private val form: CreateInvoiceForm,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfirmationState())
    val state: StateFlow<InvoiceConfirmationState> = _state

    private val _events = MutableSharedFlow<InvoiceConfirmationEvent>()
    val events = _events.asSharedFlow()

    fun initState() {
        _state.update {
            it.copy(
                companyName = session.company.name,
                customerName = form.customerName,
                issueDate = form.issueDate.toLocalDate(TimeZone.UTC),
                dueDate = form.dueDate.toLocalDate(TimeZone.UTC),
                invoiceNumber = form.invoiceNumber,
                activities = form.activities
            )
        }
    }

    fun submitInvoice() = Unit
}
