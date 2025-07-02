package io.github.alaksion.invoicer.foundation.session.di

import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.session.SessionImpl
import io.github.alaksion.invoicer.foundation.session.SessionUpdater
import org.koin.dsl.binds
import org.koin.dsl.module

val sessionDiModule = module {
    single<Session> { SessionImpl }

    single { SessionImpl } binds arrayOf(Session::class, SessionUpdater::class)
}
