package io.github.monolithic.features.home.di

import io.github.monolithic.features.home.domain.services.GetHomeDetailsService
import io.github.monolithic.features.home.domain.services.GetHomeDetailsServiceImpl
import io.github.monolithic.features.home.presentation.screens.home.HomeScreenModel
import io.github.monolithic.features.home.presentation.screens.home.tabs.account.AccountTabScreenModel
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTabScreenModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val homePresentationDiModule = module {
    factory<AccountTabScreenModel> {
        AccountTabScreenModel(
            signOutService = get()
        )
    }

    factory<WelcomeTabScreenModel> {
        WelcomeTabScreenModel(
            session = get(),
            homeDetailsService = get(),
            dispatchers = Dispatchers.Default,
            homeRefreshBus = get()
        )
    }

    factory {
        HomeScreenModel(
            session = get()
        )
    }

    factory<GetHomeDetailsService> {
        GetHomeDetailsServiceImpl(
            invoiceRepository = get(),
            session = get()
        )
    }
}
