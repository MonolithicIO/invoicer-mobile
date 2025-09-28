package io.github.monolithic.invoicer.foundation.auth.domain.services

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEventBus

interface SignInCommandManager {
    suspend fun resolveCommand(command: SignInCommand)
}

sealed interface SignInCommand {
    data class Google(val googleSessionToken: String) : SignInCommand
    data class Credential(val userName: String, val password: String) : SignInCommand
}

internal class SignInCommandManagerImpl(
    private val authRepository: AuthRepository,
    private val authTokenRepository: AuthTokenRepository,
    private val authEventBus: AuthEventBus,
) : SignInCommandManager {

    override suspend fun resolveCommand(command: SignInCommand) {
        val authToken = when (command) {
            is SignInCommand.Credential -> authRepository.signIn(
                email = command.userName,
                password = command.password
            )

            is SignInCommand.Google -> authRepository.googleSignIn(command.googleSessionToken)
        }

        authTokenRepository.storeAuthTokens(
            accessToken = authToken.accessToken,
            refreshToken = authToken.refreshToken
        )

        authEventBus.publishEvent(AuthEvent.SignedIn)
    }
}
