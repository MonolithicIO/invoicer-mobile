package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.auth.firebase.FirebaseHelper
import io.github.monolithic.invoicer.foundation.watchers.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus

interface SignOutService {
    suspend fun signOut()
}

internal class SignOutHandler(
    private val authRepository: AuthRepository,
    private val authEventBus: AuthEventBus,
    private val firebaseHelper: FirebaseHelper,
) : SignOutService {
    override suspend fun signOut() {
        authRepository.clearAuthTokens()
        firebaseHelper.signOut()
        authEventBus.publishEvent(AuthEvent.SignedOut)
    }
}
