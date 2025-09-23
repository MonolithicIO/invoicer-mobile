package io.github.monolithic.invoicer.foundation.auth.domain.repository

import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens

interface AuthTokenRepository {
    suspend fun storeAuthTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun getAuthTokens(): AuthTokens?
}
