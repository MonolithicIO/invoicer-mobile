package io.github.monolithic.invoicer.features.auth.presentation.screens.startup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.auth.domain.model.StartupDestination
import io.github.monolithic.invoicer.features.auth.domain.services.ResolveStartupDestinationService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

internal class StartupScreenModel(
    private val startupDestinationService: ResolveStartupDestinationService,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _events = MutableSharedFlow<StartupDestination>()
    val events = _events.asSharedFlow()

    fun startApp() {
        screenModelScope.launch(dispatcher) {
            val destination = startupDestinationService.resolveDestination()
            _events.emit(destination)
        }
    }
}
