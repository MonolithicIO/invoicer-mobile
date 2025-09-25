package io.github.monolithic.features.home.presentation.screens.home.tabs.account

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import kotlinx.coroutines.launch

internal class AccountTabScreenModel(
    private val signOutService: SignOutService,
) : ScreenModel {

    fun signOut() {
        screenModelScope.launch {
            signOutService.signOut()
        }
    }
}
