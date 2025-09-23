package io.github.monolithic.invoicer.foundation.auth.domain.repository

import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens

interface AuthRepository {
    suspend fun signUp(
        email: String,
        confirmEmail: String,
        password: String
    )

    suspend fun signIn(
        email: String,
        password: String
    ): AuthTokens

    suspend fun googleSignIn(
        token: String
    ): AuthTokens

    /**
     * Removes any stored authentication tokens from the local storage.
     * */
    suspend fun clearAuthTokens()

    suspend fun refreshSession(
        refreshToken: String,
    ): AuthTokens
}
