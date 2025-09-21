package io.github.monolithic.invoicer.foundation.auth.data.repository

import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthStorage
import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository

internal class AuthTokenRepositoryImpl(
    private val storage: AuthStorage
) : AuthTokenRepository {

    override suspend fun storeAuthTokens(accessToken: String, refreshToken: String) =
        storage.storeAuthTokens(
            accessToken = accessToken,
            refreshToken = refreshToken
        )

    override suspend fun getAuthTokens(): AuthTokens? {
        return storage.getAuthTokens()
    }
}
