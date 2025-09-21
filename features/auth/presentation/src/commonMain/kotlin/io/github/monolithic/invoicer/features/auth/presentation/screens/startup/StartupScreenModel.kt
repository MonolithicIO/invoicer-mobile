package io.github.monolithic.invoicer.features.auth.presentation.screens.startup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import kotlinx.coroutines.launch

internal class StartupScreenModel(
//    private val resumeSessionService: ResumeSessionService,
) : ScreenModel {

    fun startApp() {
        screenModelScope.launch {
//            resumeSessionService.resumeSession()
        }
    }
}
