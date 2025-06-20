package io.github.alaksion.invoicer.features.customer.presentation.di

import io.github.alaksion.invoicer.features.customer.presentation.screens.create.CreateCustomerScreenModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val customerPresentationDiModule = module {
    factory {
        CreateCustomerScreenModel(
            session = get(),
            dispatcher = Dispatchers.Default,
            customerRepository = get()
        )
    }
}