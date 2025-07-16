package io.github.monolithic.invoicer.foundation.watchers.di

import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBusManager
import io.github.monolithic.invoicer.foundation.watchers.NewInvoicePublisher
import io.github.monolithic.invoicer.foundation.watchers.RefreshBeneficiaryPublisher
import io.github.monolithic.invoicer.foundation.watchers.RefreshIntermediaryPublisher
import org.koin.dsl.module

val watchersDiModule = module {
    single { RefreshBeneficiaryPublisher() }
    single { RefreshIntermediaryPublisher() }
    single { NewInvoicePublisher() }
    single<AuthEventBus> { AuthEventBusManager }
}
