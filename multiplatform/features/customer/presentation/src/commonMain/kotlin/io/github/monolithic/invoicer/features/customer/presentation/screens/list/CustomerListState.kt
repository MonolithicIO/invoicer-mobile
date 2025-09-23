package io.github.monolithic.invoicer.features.customer.presentation.screens.list

import io.github.monolithic.invoicer.features.customer.domain.model.CustomerListItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class CustomerListState(
    val customers: ImmutableList<CustomerListItemModel> = persistentListOf(),
    val mode: CustomerListMode = CustomerListMode.Content,
    val nextPageLoading: Boolean = false
)

internal enum class CustomerListMode {
    Content,
    Error,
    Loading;
}


internal sealed interface CustomerListEvent {
    data object NextPageFailed: CustomerListEvent
}
