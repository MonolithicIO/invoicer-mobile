package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities

internal data class InvoiceActivitiesCallbacks(
    val onChangeDescription: (String) -> Unit,
    val onChangeUnitPrice: (String) -> Unit,
    val onChangeQuantity: (String) -> Unit,
    val onDelete: (String) -> Unit,
    val onClearForm: () -> Unit,
    val onAddActivity: () -> Unit,
    val onBack: () -> Unit,
    val onContinue: () -> Unit,
)
