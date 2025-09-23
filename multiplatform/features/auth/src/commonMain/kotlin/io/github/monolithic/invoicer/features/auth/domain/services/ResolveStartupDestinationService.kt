package io.github.monolithic.invoicer.features.auth.domain.services

import io.github.monolithic.invoicer.features.auth.domain.model.StartupDestination
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository

internal interface ResolveStartupDestinationService {
    suspend fun resolveDestination(): StartupDestination
}

// TODO Implement logic to check the stored selected company and launch home if a valid company is found
internal class ResolveStartupDestinationServiceImpl(
    private val authTokenRepository: AuthTokenRepository,
) : ResolveStartupDestinationService {
    override suspend fun resolveDestination(): StartupDestination {

        if (!checkAuthTokenPresence()) {
            return StartupDestination.AuthMenu
        }

        return StartupDestination.SelectCompany
    }

    private suspend fun checkAuthTokenPresence(): Boolean {
        return authTokenRepository.getAuthTokens() != null
    }

}
