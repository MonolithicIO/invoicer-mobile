package io.github.monolithic.features.home.presentation.tabs.welcome

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.foundation.auth.session.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

internal class WelcomeTabScreenModel(
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(WelcomeTabState())
    val state = _state.asStateFlow()

    fun loadData() {
        _state.value = WelcomeTabState(
            companyName = session.getCompany().name,
            isChangeCompanyEnabled = session.getCompany().isChangeCompanyEnabled
        )
    }
}
