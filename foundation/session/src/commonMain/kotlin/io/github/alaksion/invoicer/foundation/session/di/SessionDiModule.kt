package io.github.alaksion.invoicer.foundation.session.di

import io.github.alaksion.invoicer.foundation.session.Session
import io.github.alaksion.invoicer.foundation.session.SessionImpl
import org.koin.dsl.module

val sessionDiModule = module {
    single<Session> { SessionImpl }
}