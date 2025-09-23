package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository

interface RefreshSessionService {
    suspend fun refresh(): AuthTokens
}

internal class RefreshSessionServiceImpl(
    private val authRepository: AuthRepository,
    private val authTokenRepository: AuthTokenRepository
) : RefreshSessionService {

    override suspend fun refresh(): AuthTokens {
        val currentTokens = authTokenRepository.getAuthTokens()
        val newTokens =
            authRepository.refreshSession(
                refreshToken = currentTokens?.refreshToken.orEmpty()
            )

        authTokenRepository.storeAuthTokens(
            accessToken = newTokens.accessToken,
            refreshToken = newTokens.refreshToken
        )

        return newTokens
    }
}
