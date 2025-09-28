package io.github.monolithic.invoicer.foundation.network.client

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.auth.domain.services.RefreshSessionService
import io.github.monolithic.invoicer.foundation.auth.domain.services.SignOutService
import io.github.monolithic.invoicer.foundation.network.InvoicerHttpError
import io.github.monolithic.invoicer.foundation.network.NetworkBuildConfig
import io.github.monolithic.invoicer.foundation.network.RequestError
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEventBus
import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.Logging
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

internal class HttpClientProvider(
    private val authTokenRepository: AuthTokenRepository,
    private val refreshSessionService: RefreshSessionService,
    private val signOutService: SignOutService
) {

    fun provideClient(): HttpClient = HttpClient {
        expectSuccess = true
        contentNegotiation()
        log()
        responseValidation()
        defaultRequest()
        authorization()
    }

    private fun HttpClientConfig<*>.contentNegotiation() {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    ignoreUnknownKeys = true
                    encodeDefaults = true
                }
            )
        }
    }

    private fun HttpClientConfig<*>.log() {
        install(Logging)
    }

    private fun HttpClientConfig<*>.responseValidation() {
        HttpResponseValidator {
            handleResponseExceptionWithRequest { exception, request ->
                val clientException =
                    exception as? ClientRequestException
                        ?: return@handleResponseExceptionWithRequest

                runCatching {
                    clientException.response.body<InvoicerHttpError>()
                }.onSuccess { parsedBody ->
                    throw RequestError.Http(
                        httpCode = clientException.response.status.value,
                        errorCode = parsedBody.errorCode,
                        message = parsedBody.message
                    )
                }.onFailure {
                    throw RequestError.Other(it)
                }
            }
        }
    }

    private fun HttpClientConfig<*>.defaultRequest() {
        defaultRequest {
            host = NetworkBuildConfig.API_URL
            contentType(ContentType.Application.Json)
        }
    }

    private fun HttpClientConfig<*>.authorization() {
        install(Auth) {
            bearer {
                loadTokens {
                    val currentTokens = runBlocking { authTokenRepository.getAuthTokens() }

                    BearerTokens(
                        accessToken = currentTokens?.accessToken.orEmpty(),
                        refreshToken = currentTokens?.refreshToken.orEmpty()
                    )
                }

                // TODO Maybe use a message queue instead of dealing with sign out here?
                refreshTokens {
                    runCatching {
                        refreshSessionService.refresh()
                    }.fold(
                        onSuccess = {
                            BearerTokens(
                                accessToken = it.accessToken,
                                refreshToken = it.refreshToken
                            )
                        },
                        onFailure = {
                            signOutService.signOut()
                            BearerTokens(
                                accessToken = "",
                                refreshToken = ""
                            )
                        }
                    )
                }
            }
        }
    }
}
