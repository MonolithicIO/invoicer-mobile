package io.github.monolithic.invoicer.sharedApp.init.di

import io.github.monolithic.features.home.presentation.di.homePresentationDiModule
import io.github.monolithic.invoicer.features.auth.di.featureAuthPresentationDiModule
import io.github.monolithic.invoicer.features.company.presentation.di.companyPresentationDiModule
import io.github.monolithic.invoicer.features.company.services.di.companyServicesDiModule
import io.github.monolithic.invoicer.features.customer.di.customerServiceDiModule
import io.github.monolithic.invoicer.features.customer.presentation.di.customerPresentationDiModule
import io.github.monolithic.invoicer.features.invoice.presentation.di.invoicePresentationDiModule
import io.github.monolithic.invoicer.features.invoice.services.di.invoiceServicesDiModule
import io.github.monolithic.invoicer.features.qrcodeSession.di.qrCodeSessionDi
import io.github.monolithic.invoicer.foundation.auth.di.foundationAuthDiModule
import io.github.monolithic.invoicer.foundation.network.di.networkDiModule
import io.github.monolithic.invoicer.foundation.platform.platformDiModule
import io.github.monolithic.invoicer.foundation.utils.di.utilsDiModule
import io.github.monolithic.invoicer.foundation.watchers.di.watchersDiModule
import org.koin.dsl.module

internal val appModule = module {
    includes(
        featureAuthPresentationDiModule,
        networkDiModule,
        foundationAuthDiModule,
        homePresentationDiModule,
        utilsDiModule,
        invoiceServicesDiModule,
        invoicePresentationDiModule,
        qrCodeSessionDi,
        watchersDiModule,
        companyPresentationDiModule,
        companyServicesDiModule,
        customerServiceDiModule,
        customerPresentationDiModule,
        platformDiModule
    )
}
