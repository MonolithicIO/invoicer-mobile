package io.github.monolithic.features.home.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.foundation.auth.session.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class HomeScreenModel(
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(HomeScreenState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(companyName = session.getCompany().name)
        }
    }
}
