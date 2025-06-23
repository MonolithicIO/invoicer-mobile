package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow

internal class InvoiceConfirmationScreenModel : ScreenModel {

    private val _state = MutableStateFlow(InvoiceConfirmationState())
    val state: StateFlow<InvoiceConfirmationState> = _state

    private val _events = MutableSharedFlow<InvoiceConfirmationEvent>()
    val events = _events.asSharedFlow()

    fun initState() = Unit

    fun submitInvoice() = Unit
}
