package io.github.monolithic.features.home.presentation.screens.home.tabs.welcome

import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.components.LatestInvoiceUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class WelcomeTabState(
    val companyName: String = "",
    val latestInvoices: ImmutableList<LatestInvoiceUiModel> = persistentListOf(),
    val mode: WelcomeTabMode = WelcomeTabMode.Loading
)

enum class WelcomeTabMode {
    Loading,
    Error,
    Content,
}
