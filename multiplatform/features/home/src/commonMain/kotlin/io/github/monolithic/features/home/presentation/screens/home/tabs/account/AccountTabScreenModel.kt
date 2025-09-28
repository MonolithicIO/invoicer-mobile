package io.github.monolithic.features.home.presentation.screens.home.tabs.account

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import io.github.monolithic.invoicer.foundation.auth.session.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class AccountTabScreenModel(
    private val signOutService: SignOutService,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(AccountTabState())
    val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                currentCompanyName = session.getCompany().name
            )
        }
    }

    fun signOut() {
        screenModelScope.launch {
            signOutService.signOut()
        }
    }
}
