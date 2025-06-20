package io.github.alaksion.invoicer.features.customer.presentation.di

import io.github.alaksion.invoicer.features.customer.presentation.screens.create.CreateCustomerScreenModel
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val customerPresentationDiModule = module {
    factory {
        CreateCustomerScreenModel(
            session = Session,
            dispatcher = Dispatchers.Default,
            customerRepository = get()
        )
    }
}