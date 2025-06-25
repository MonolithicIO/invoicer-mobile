package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.customer

import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class InvoiceCustomerState(
    val customers: ImmutableList<CustomerListItemModel> = persistentListOf(),
    val selectedCustomerId: String? = null,
    val mode: InvoiceCustomerMode = InvoiceCustomerMode.Content,
) {
    val isButtonEnabled: Boolean = selectedCustomerId != null
}

internal enum class InvoiceCustomerMode {
    Content,
    Loading,
    Error;
}
