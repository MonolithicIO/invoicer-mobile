package io.github.monolithic.invoicer.foundation.network.di

import io.github.monolithic.invoicer.foundation.network.client.NetworkEngine
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkDiModule = module {
    single<HttpClient> { NetworkEngine }
}
