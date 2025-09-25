package io.github.monolithic.invoicer.foundation.watchers.di

import io.github.monolithic.invoicer.foundation.watchers.bus.NewInvoiceEventBus
import io.github.monolithic.invoicer.foundation.watchers.bus.RefreshBeneficiaryPublisher
import io.github.monolithic.invoicer.foundation.watchers.bus.RefreshIntermediaryPublisher
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEventBus
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.AuthEventBusManager
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.HomeRefreshBus
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.HomeRefreshBusImpl
import org.koin.dsl.module

val watchersDiModule = module {
    single { RefreshBeneficiaryPublisher() }
    single { RefreshIntermediaryPublisher() }
    single { NewInvoiceEventBus() }
    single<AuthEventBus> { AuthEventBusManager }
    single<HomeRefreshBus> { HomeRefreshBusImpl }
}
