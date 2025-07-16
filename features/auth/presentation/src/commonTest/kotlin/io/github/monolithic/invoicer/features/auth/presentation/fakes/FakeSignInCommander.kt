package io.github.monolithic.invoicer.features.auth.presentation.fakes

import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommand
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManager

class FakeSignInCommander : SignInCommandManager {

    var failure: Throwable? = null
    var signInCommand: SignInCommand? = null

    override suspend fun resolveCommand(command: SignInCommand) {
        failure?.let { throw it }
        signInCommand = command
    }
}
