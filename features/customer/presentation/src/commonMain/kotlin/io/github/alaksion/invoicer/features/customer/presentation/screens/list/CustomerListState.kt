package io.github.alaksion.invoicer.features.customer.presentation.screens.list

import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class CustomerListState(
    val customers: ImmutableList<CustomerListItemModel> = persistentListOf(),
    val mode: CustomerListMode = CustomerListMode.Content,
)

internal enum class CustomerListMode {
    Content,
    Error,
    Loading;
}
