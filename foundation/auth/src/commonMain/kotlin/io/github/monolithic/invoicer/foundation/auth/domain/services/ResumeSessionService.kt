package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.watchers.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus


// TODO -> Apagar essa merda, isso sequer faz sentido
interface ResumeSessionService {
    suspend fun resumeSession(): Boolean
}

// lixo
internal class ResumeSessionServiceImpl(
    private val authTokenRepository: AuthTokenRepository,
    private val authEventBus: AuthEventBus,
) : ResumeSessionService {

    override suspend fun resumeSession(): Boolean {
        val tokens = authTokenRepository.getAuthTokens()
        return if (tokens != null) {
            authEventBus.publishEvent(AuthEvent.SignedIn)
            true
        } else {
            authEventBus.publishEvent(AuthEvent.SignedOut)
            false
        }
    }
}
