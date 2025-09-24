package io.github.monolithic.features.home.presentation.screens.home.tabs.settings

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import kotlinx.coroutines.launch

internal class SettingsScreenModel(
    private val signOutService: SignOutService,
) : ScreenModel {

    fun signOut() {
        screenModelScope.launch {
            signOutService.signOut()
        }
    }
}
