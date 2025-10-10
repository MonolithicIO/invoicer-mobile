package io.github.monolithic.invoicer.features.company.presentation.screens.select

import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class SelectCompanyState(
    val mode: SelectCompanyMode = SelectCompanyMode.Loading,
    val companies: ImmutableList<ListCompaniesItemModel> = persistentListOf(),
    val selectedCompanyId: String? = null
)

internal sealed interface SelectCompanyEvent {
    data object ContinueToHome : SelectCompanyEvent
    data object NoCompanySelected: SelectCompanyEvent
}

internal enum class SelectCompanyMode {
    Loading,
    List,
    Error,
    EmptyState,
}
