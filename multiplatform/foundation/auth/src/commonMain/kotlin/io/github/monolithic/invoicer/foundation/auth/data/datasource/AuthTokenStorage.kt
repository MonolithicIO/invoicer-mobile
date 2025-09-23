package io.github.monolithic.invoicer.foundation.auth.data.datasource

import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens
import io.github.monolithic.invoicer.foundation.storage.LocalStorage

internal interface AuthStorage {
    suspend fun storeAuthTokens(
        accessToken: String,
        refreshToken: String
    )

    suspend fun getAuthTokens(): AuthTokens?

    suspend fun clearTokens()
}

internal class AuthStorageImpl(
    private val localStorage: LocalStorage,
) : AuthStorage {

    override suspend fun storeAuthTokens(accessToken: String, refreshToken: String) {
        localStorage.setString(
            key = REFRESH_TOKEN_KEY,
            value = refreshToken
        )

        localStorage.setString(
            key = ACCESS_TOKEN_KEY,
            value = accessToken
        )
    }

    override suspend fun getAuthTokens(): AuthTokens? {
        val refreshToken = localStorage.getString(REFRESH_TOKEN_KEY)
        val accessToken = localStorage.getString(ACCESS_TOKEN_KEY)

        if (refreshToken == null || accessToken == null) {
            return null
        }

        return AuthTokens(
            refreshToken = refreshToken,
            accessToken = accessToken
        )
    }

    override suspend fun clearTokens() {
        localStorage.clear(REFRESH_TOKEN_KEY)
        localStorage.clear(ACCESS_TOKEN_KEY)
    }

    private companion object {
        const val REFRESH_TOKEN_KEY = "invoicer-refresh"
        const val ACCESS_TOKEN_KEY = "invoicer-access"
    }
}
