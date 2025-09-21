package io.github.monolithic.invoicer.foundation.auth.di

import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthRemoteDataSource
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthRemoteDataSourceImpl
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthStorage
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthStorageImpl
import io.github.monolithic.invoicer.foundation.auth.data.repository.AuthRepositoryImpl
import io.github.monolithic.invoicer.foundation.auth.data.repository.AuthTokenRepositoryImpl
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.auth.domain.services.ResumeSessionService
import io.github.monolithic.invoicer.foundation.auth.domain.services.ResumeSessionServiceImpl
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManager
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManagerResolver
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutHandler
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

val foundationAuthDiModule = module {
    includes(authPlatformModule)

    factory<AuthStorage> {
        AuthStorageImpl(
            localStorage = get(),
        )
    }
    factory<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(
            httpClient = get(),
            dispatcher = Dispatchers.IO
        )
    }
    factory<AuthRepository> {
        AuthRepositoryImpl(
            authStorage = get(),
            remoteDataSource = get()
        )
    }
    factory<AuthTokenRepository> { AuthTokenRepositoryImpl(storage = get()) }

    factory<SignInCommandManager> {
        SignInCommandManagerResolver(
            authRepository = get(),
            authTokenRepository = get(),
            authEventBus = get(),
            session = get()
        )
    }

    factory<SignOutService> {
        SignOutHandler(
            authEventBus = get(),
            authRepository = get(),
            firebaseHelper = get(),
            session = get()
        )
    }

    factory<ResumeSessionService> {
        ResumeSessionServiceImpl(
            authTokenRepository = get(),
            authEventBus = get(),
            sessionUpdater = get()
        )
    }
}
internal expect val authPlatformModule: Module
