package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.session.SessionTokens
import io.github.monolithic.invoicer.foundation.session.SessionUpdater
import io.github.monolithic.invoicer.foundation.watchers.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus

interface ResumeSessionService {
    suspend fun resumeSession(): Boolean
}

internal class ResumeSessionServiceImpl(
    private val authTokenRepository: AuthTokenRepository,
    private val authEventBus: AuthEventBus,
    private val sessionUpdater: SessionUpdater
) : ResumeSessionService {

    override suspend fun resumeSession(): Boolean {
        val tokens = authTokenRepository.getAuthTokens()
        return if (tokens != null) {
            sessionUpdater.updateTokens(
                SessionTokens(
                    accessToken = tokens.accessToken,
                    refreshToken = tokens.refreshToken,
                )
            )
            authEventBus.publishEvent(AuthEvent.SignedIn)
            true
        } else {
            authEventBus.publishEvent(AuthEvent.SignedOut)
            false
        }
    }
}
