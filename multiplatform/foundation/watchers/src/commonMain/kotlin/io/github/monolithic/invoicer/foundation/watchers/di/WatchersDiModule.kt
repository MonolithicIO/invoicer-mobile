package io.github.monolithic.invoicer.foundation.watchers.di

import io.github.monolithic.invoicer.foundation.watchers.bus.AuthEventBus
import io.github.monolithic.invoicer.foundation.watchers.bus.AuthEventBusManager
import io.github.monolithic.invoicer.foundation.watchers.bus.NewInvoiceEventBus
import io.github.monolithic.invoicer.foundation.watchers.bus.RefreshBeneficiaryPublisher
import io.github.monolithic.invoicer.foundation.watchers.bus.RefreshIntermediaryPublisher
import org.koin.dsl.module

val watchersDiModule = module {
    single { RefreshBeneficiaryPublisher() }
    single { RefreshIntermediaryPublisher() }
    single { NewInvoiceEventBus() }
    single<AuthEventBus> { AuthEventBusManager }
}
