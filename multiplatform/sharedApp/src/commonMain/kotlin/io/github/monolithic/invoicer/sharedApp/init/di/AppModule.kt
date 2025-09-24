package io.github.monolithic.invoicer.sharedApp.init.di

import io.github.monolithic.features.home.presentation.di.homePresentationDiModule
import io.github.monolithic.invoicer.features.auth.di.featureAuthPresentationDiModule
import io.github.monolithic.invoicer.features.company.presentation.di.companyPresentationDiModule
import io.github.monolithic.invoicer.features.company.services.di.companyServicesDiModule
import io.github.monolithic.invoicer.features.customer.di.customerServiceDiModule
import io.github.monolithic.invoicer.features.customer.presentation.di.customerPresentationDiModule
import io.github.monolithic.invoicer.features.invoice.di.invoiceDiModule
import io.github.monolithic.invoicer.features.qrcodeSession.di.qrCodeSessionDi
import io.github.monolithic.invoicer.foundation.analytics.di.analyticsDiModule
import io.github.monolithic.invoicer.foundation.auth.di.foundationAuthDiModule
import io.github.monolithic.invoicer.foundation.network.di.networkDiModule
import io.github.monolithic.invoicer.foundation.storage.di.localStorageDiModule
import io.github.monolithic.invoicer.foundation.utils.di.utilsDiModule
import io.github.monolithic.invoicer.foundation.validator.di.validatorDiModule
import io.github.monolithic.invoicer.foundation.watchers.di.watchersDiModule
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
        companyPresentationDiModule,
        companyServicesDiModule,
        customerServiceDiModule,
        customerPresentationDiModule,
    )
}
