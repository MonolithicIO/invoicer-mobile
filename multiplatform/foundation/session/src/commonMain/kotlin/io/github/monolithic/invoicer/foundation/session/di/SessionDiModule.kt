package io.github.monolithic.invoicer.foundation.session.di

import io.github.monolithic.invoicer.foundation.session.Session
import io.github.monolithic.invoicer.foundation.session.SessionImpl
import io.github.monolithic.invoicer.foundation.session.SessionUpdater
import org.koin.dsl.binds
import org.koin.dsl.module

val sessionDiModule = module {
    single<Session> { SessionImpl }

    single { SessionImpl } binds arrayOf(Session::class, SessionUpdater::class)
}
