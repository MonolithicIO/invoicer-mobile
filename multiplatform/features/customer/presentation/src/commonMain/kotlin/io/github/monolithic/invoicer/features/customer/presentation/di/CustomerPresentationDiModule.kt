package io.github.monolithic.invoicer.features.customer.presentation.di

import io.github.monolithic.invoicer.features.customer.presentation.screens.create.CreateCustomerScreenModel
import io.github.monolithic.invoicer.features.customer.presentation.screens.list.CustomerListScreenModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val customerPresentationDiModule = module {
    factory {
        CreateCustomerScreenModel(
            session = get(),
            dispatcher = Dispatchers.Default,
            customerRepository = get(),
            bus = get()
        )
    }

    factory {
        CustomerListScreenModel(
            session = get(),
            customerRepository = get(),
            dispatcher = Dispatchers.Default
        )
    }
}
