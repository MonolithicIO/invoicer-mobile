package io.github.monolithic.invoicer.foundation.network.client

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.network.NetworkBuildConfig
import io.github.monolithic.invoicer.foundation.network.RequestError
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
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

internal class HttpClientProvider(
    private val authTokenRepository: AuthTokenRepository
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
            header(
                HttpHeaders.Authorization,
                "Bearer " + runBlocking { authTokenRepository.getAuthTokens()?.accessToken })
        }
    }

    private fun HttpClientConfig<*>.authorization() {
        install(Auth) {
            bearer {
                loadTokens {
                    val tokens = runBlocking { authTokenRepository.getAuthTokens() }

                    BearerTokens(
                        accessToken = tokens?.accessToken.orEmpty(),
                        refreshToken = tokens?.refreshToken.orEmpty()
                    )
                }

                refreshTokens {
                    BearerTokens(
                        accessToken = "",
                        refreshToken = ""
                    )
                }
            }
        }
    }

}
