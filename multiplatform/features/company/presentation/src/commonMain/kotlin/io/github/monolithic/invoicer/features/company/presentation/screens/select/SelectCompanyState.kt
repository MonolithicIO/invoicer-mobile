package io.github.monolithic.invoicer.features.company.presentation.screens.select

import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesItemModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class SelectCompanyState(
    val mode: SelectCompanyMode = SelectCompanyMode.Loading,
    val companies: ImmutableList<ListCompaniesItemModel> = persistentListOf()
)

internal sealed interface SelectCompanyEvent {
    data object ContinueToHome : SelectCompanyEvent
}

internal enum class SelectCompanyMode {
    Loading,
    List,
    Error,
    CreateCompany,
}
