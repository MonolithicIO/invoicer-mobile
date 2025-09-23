package io.github.monolithic.invoicer.features.qrcodeSession.data.repository

import io.github.monolithic.invoicer.features.qrcodeSession.data.model.QrCodeTokenDetailsResponse
import io.github.monolithic.invoicer.features.qrcodeSession.data.model.toDomain
import io.github.monolithic.invoicer.features.qrcodeSession.domain.model.QrCodeTokenDetailsModel
import io.github.monolithic.invoicer.features.qrcodeSession.domain.repository.QrCodeTokenRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

internal class QrCodeTokenRepositoryImpl(
    private val httpClient: HttpClient,
    private val dispatcher: CoroutineDispatcher
) : QrCodeTokenRepository {

    override suspend fun getQrCodeDetails(token: String): QrCodeTokenDetailsModel {
        return withContext(dispatcher) {
            httpClient
                .get("/v1/login_code/${token}")
                .body<QrCodeTokenDetailsResponse>()
                .toDomain()
        }
    }

    override suspend fun consumeQrCode(token: String) {
        return withContext(dispatcher) {
            httpClient
                .post("/v1/login_code/$token/consume")
        }
    }
}
