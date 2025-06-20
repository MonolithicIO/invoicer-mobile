package io.github.alaksion.features.home.presentation.tabs.welcome

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class WelcomeTabScreenModel(
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(WelcomeTabState())
    val state = _state.asStateFlow()

    fun loadData() {
        _state.value = WelcomeTabState(
            companyName = session.company.name,
            isChangeCompanyEnabled = session.company.isChangeCompanyEnabled
        )
    }
}
