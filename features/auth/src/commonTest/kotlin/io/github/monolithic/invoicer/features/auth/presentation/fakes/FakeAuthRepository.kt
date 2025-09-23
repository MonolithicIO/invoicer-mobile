package io.github.monolithic.invoicer.features.auth.presentation.fakes

import io.github.monolithic.invoicer.foundation.auth.domain.model.AuthTokens
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository

class FakeAuthRepository : AuthRepository {

    var signUpCalls = 0
        private set

    var signUpError: Throwable? = null

    override suspend fun signUp(
        email: String,
        confirmEmail: String,
        password: String
    ) {
        signUpCalls++
        signUpError?.let { throw it }
    }

    override suspend fun signIn(
        email: String,
        password: String
    ): AuthTokens {
        TODO("Not yet implemented")
    }

    override suspend fun googleSignIn(token: String): AuthTokens {
        TODO("Not yet implemented")
    }

    override suspend fun clearAuthTokens() {
        TODO("Not yet implemented")
    }

    override suspend fun refreshSession(refreshToken: String): AuthTokens {
        TODO("Not yet implemented")
    }
}
