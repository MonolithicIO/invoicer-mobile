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

    suspend fun signOut()

    suspend fun refreshSession(
        refreshToken: String,
    ): AuthTokens
}
