package io.github.monolithic.invoicer.foundation.auth.di

import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthRemoteDataSource
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthRemoteDataSourceImpl
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthStorage
import io.github.monolithic.invoicer.foundation.auth.data.datasource.AuthStorageImpl
import io.github.monolithic.invoicer.foundation.auth.data.repository.AuthRepositoryImpl
import io.github.monolithic.invoicer.foundation.auth.data.repository.AuthTokenRepositoryImpl
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthRepository
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.auth.domain.services.RefreshSessionService
import io.github.monolithic.invoicer.foundation.auth.domain.services.RefreshSessionServiceImpl
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManager
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignInCommandManagerImpl
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutHandler
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import io.github.monolithic.invoicer.foundation.auth.session.Session
import io.github.monolithic.invoicer.foundation.auth.session.SessionImpl
import io.github.monolithic.invoicer.foundation.auth.session.SessionUpdater
import io.github.monolithic.invoicer.foundation.utils.koinTags.NetworkDiTags
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.qualifier.named
import org.koin.dsl.binds
import org.koin.dsl.module

val foundationAuthDiModule = module {

    factory<AuthStorage> {
        AuthStorageImpl(
            localStorage = get(),
        )
    }
    factory<AuthRemoteDataSource> {
        AuthRemoteDataSourceImpl(
            httpClient = get(named(NetworkDiTags.AUTH_HTTP_CLIENT)),
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
        SignInCommandManagerImpl(
            authRepository = get(),
            authTokenRepository = get(),
            authEventBus = get(),
        )
    }

    factory<SignOutService> {
        SignOutHandler(
            authEventBus = get(),
            authRepository = get(),
            firebaseHelper = get(),
            localStorage = get()
        )
    }

    factory<RefreshSessionService> {
        RefreshSessionServiceImpl(
            authRepository = get(),
            authTokenRepository = get()
        )
    }

    single<Session> { SessionImpl }

    single { SessionImpl } binds arrayOf(Session::class, SessionUpdater::class)
}
