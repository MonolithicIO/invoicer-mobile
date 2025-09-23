package io.github.monolithic.invoicer.features.qrcodeSession.di

import io.github.monolithic.invoicer.features.qrcodeSession.data.repository.QrCodeTokenRepositoryImpl
import io.github.monolithic.invoicer.features.qrcodeSession.domain.repository.QrCodeTokenRepository
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation.AuthorizationConfirmationScreenModel
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.scan.AuthorizationScanScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val qrCodeSessionDi = module {
    factory<QrCodeTokenRepository> {
        QrCodeTokenRepositoryImpl(
            httpClient = get(),
            dispatcher = Dispatchers.IO
        )
    }

    factory {
        AuthorizationScanScreenModel(
            uuidValidator = get(),
            dispatcher = Dispatchers.Default
        )
    }

    factory {
        AuthorizationConfirmationScreenModel(
            qrCodeTokenRepository = get(),
            dispatcher = Dispatchers.Default
        )
    }
}
