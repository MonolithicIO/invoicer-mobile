package io.github.monolithic.invoicer.features.qrcodeSession.domain.repository

import io.github.monolithic.invoicer.features.qrcodeSession.domain.model.QrCodeTokenDetailsModel

internal interface QrCodeTokenRepository {
    suspend fun getQrCodeDetails(token: String): QrCodeTokenDetailsModel
    suspend fun consumeQrCode(token: String)
}
