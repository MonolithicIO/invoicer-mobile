package io.github.monolithic.invoicer.features.customer.di

import io.github.monolithic.invoicer.features.customer.data.repository.CustomerRepositoryImpl
import io.github.monolithic.invoicer.features.customer.domain.repository.CustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val customerServiceDiModule = module {
    factory<CustomerRepository> {
        CustomerRepositoryImpl(
            httpWrapper = get(),
            dispatcher = Dispatchers.IO
        )
    }
}
