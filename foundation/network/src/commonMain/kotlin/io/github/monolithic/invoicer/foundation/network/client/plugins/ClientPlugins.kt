package io.github.monolithic.invoicer.foundation.network.client.plugins

import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository
import io.github.monolithic.invoicer.foundation.network.NetworkBuildConfig
import io.github.monolithic.invoicer.foundation.network.RequestError
import io.github.monolithic.invoicer.foundation.network.client.InvoicerHttpError
import io.ktor.client.HttpClientConfig
import io.ktor.client.call.body
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpResponseValidator
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

internal fun HttpClientConfig<*>.setupClient(
    tokenRepository: AuthTokenRepository
) {
    expectSuccess = true
    contentNegotiation()
    log()
    defaultRequest(tokenRepository)
    responseValidation()
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

private fun HttpClientConfig<*>.defaultRequest(tokenRepository: AuthTokenRepository) {
    defaultRequest {
        host = NetworkBuildConfig.API_URL
        contentType(ContentType.Application.Json)
        header(
            HttpHeaders.Authorization,
            "Bearer " + runBlocking { tokenRepository.getAuthTokens()?.accessToken })
    }
}

