package io.github.monolithic.invoicer.foundation.network.di

import io.github.monolithic.invoicer.foundation.network.client.AuthHttpClientProvider
import io.github.monolithic.invoicer.foundation.network.client.HttpClientProvider
import io.github.monolithic.invoicer.foundation.utils.koinTags.NetworkDiTags
import io.ktor.client.HttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module

val networkDiModule = module {

    // Workaround for cyclic dependency graph
    factory<HttpClient>(named(NetworkDiTags.REGULAR_HTTP_CLIENT)) {
        val provider = get<HttpClientProvider>()
        provider.provideClient()
    }

    factory<HttpClient>(named(NetworkDiTags.AUTH_HTTP_CLIENT)) {
        val provider = get<AuthHttpClientProvider>()
        provider.provideClient()
    }

    factory {
        HttpClientProvider(
            authTokenRepository = get(),
            refreshSessionService = get(),
            authEventBus = get(),
            signOutService = get()
        )
    }

    factory {
        AuthHttpClientProvider()
    }
}
