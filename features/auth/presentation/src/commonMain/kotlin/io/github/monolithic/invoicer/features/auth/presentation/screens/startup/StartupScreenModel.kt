package io.github.monolithic.invoicer.features.auth.presentation.screens.startup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.utils.logger.InvoicerLogger
import io.github.monolithic.invoicer.foundation.watchers.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus
import kotlinx.coroutines.launch

internal class StartupScreenModel(
    private val authTokenRepository: AuthTokenRepository,
    private val authEventBus: AuthEventBus,
    private val logger: InvoicerLogger
) : ScreenModel {

    fun startApp() {
        screenModelScope.launch {
            val tokens = authTokenRepository.getAuthTokens()
            if (tokens != null) {
                authEventBus.publishEvent(AuthEvent.SignedIn)
                logger.logDebug(
                    message = "User is already signed in, resuming app",
                    key = "StartupScreenModel"
                )
            } else {
                authEventBus.publishEvent(AuthEvent.SignedOut)
                logger.logDebug(
                    message = "User not authenticated, redirecting to sign in",
                    key = "StartupScreenModel"
                )
            }
        }
    }
}
