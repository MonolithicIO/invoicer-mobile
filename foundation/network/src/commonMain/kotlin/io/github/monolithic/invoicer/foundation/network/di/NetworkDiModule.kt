package io.github.monolithic.invoicer.foundation.network.di

import io.github.monolithic.invoicer.foundation.network.client.HttpClientProvider
import io.ktor.client.HttpClient
import org.koin.dsl.module

val networkDiModule = module {
    single<HttpClient> {
        val provider = get<HttpClientProvider>()
        provider.provideClient()
    }

    factory {
        HttpClientProvider(
            authTokenRepository = get()
        )
    }
}
