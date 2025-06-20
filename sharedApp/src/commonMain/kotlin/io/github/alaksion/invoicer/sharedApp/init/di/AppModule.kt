package io.github.alaksion.invoicer.sharedApp.init.di

import io.github.alaksion.features.home.presentation.di.homePresentationDiModule
import io.github.alaksion.invoicer.features.auth.presentation.di.featureAuthPresentationDiModule
import io.github.alaksion.invoicer.features.company.di.companyDiModule
import io.github.alaksion.invoicer.features.customer.di.customerServiceDiModule
import io.github.alaksion.invoicer.features.customer.presentation.di.customerPresentationDiModule
import io.github.alaksion.invoicer.features.invoice.di.invoiceDiModule
import io.github.alaksion.invoicer.features.qrcodeSession.di.qrCodeSessionDi
import io.github.alaksion.invoicer.foundation.analytics.di.analyticsDiModule
import io.github.alaksion.invoicer.foundation.auth.di.foundationAuthDiModule
import io.github.alaksion.invoicer.foundation.network.di.networkDiModule
import io.github.alaksion.invoicer.foundation.session.di.sessionDiModule
import io.github.alaksion.invoicer.foundation.storage.di.localStorageDiModule
import io.github.alaksion.invoicer.foundation.utils.di.utilsDiModule
import io.github.alaksion.invoicer.foundation.validator.di.validatorDiModule
import io.github.alaksion.invoicer.foundation.watchers.di.watchersDiModule
import org.koin.dsl.module

internal val appModule = module {
    includes(
        featureAuthPresentationDiModule,
        networkDiModule,
        validatorDiModule,
        localStorageDiModule,
        foundationAuthDiModule,
        homePresentationDiModule,
        utilsDiModule,
        invoiceDiModule,
        qrCodeSessionDi,
        watchersDiModule,
        analyticsDiModule,
        companyDiModule,
        customerServiceDiModule,
        customerPresentationDiModule,
        sessionDiModule
    )
}
