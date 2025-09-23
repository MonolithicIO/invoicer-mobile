package io.github.monolithic.invoicer.features.auth.presentation.screens.startup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.auth.domain.model.StartupDestination
import io.github.monolithic.invoicer.features.auth.domain.services.ResolveStartupDestinationService
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisser
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class StartupScreenModel(
    private val startupDestinationService: ResolveStartupDestinationService,
    private val dispatcher: CoroutineDispatcher,
    private val splashScreenDismisser: SplashScreenDismisser
) : ScreenModel {

    // TODO There seems to be a race condition in the UI Layer so replay here serves to solve this.
    private val _events = MutableSharedFlow<StartupDestination>(replay = 1)
    val events = _events.asSharedFlow()

    fun startApp() {
        screenModelScope.launch(dispatcher) {
            val destination = startupDestinationService.resolveDestination()
            _events.emit(destination)
        }
    }

    fun dismissSplashScreen() {
        splashScreenDismisser.dismiss()
    }
}
