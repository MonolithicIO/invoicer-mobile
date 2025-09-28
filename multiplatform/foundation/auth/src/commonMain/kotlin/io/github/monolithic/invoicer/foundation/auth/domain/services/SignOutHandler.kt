package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.platform.firebaseAuth.FirebaseHelper
import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage
import io.github.monolithic.invoicer.foundation.platform.storage.StorageKeys
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEventBus

interface SignOutService {
    suspend fun signOut()
}

internal class SignOutHandler(
    private val authRepository: AuthRepository,
    private val authEventBus: AuthEventBus,
    private val firebaseHelper: FirebaseHelper,
    private val localStorage: LocalStorage
) : SignOutService {
    override suspend fun signOut() {
        authRepository.clearAuthTokens()
        firebaseHelper.signOut()
        clearUserStorage()
        authEventBus.publishEvent(AuthEvent.SignedOut)
    }

    private fun clearUserStorage() {
        localStorage.clear(StorageKeys.SelectedCompany.key)
    }
}
