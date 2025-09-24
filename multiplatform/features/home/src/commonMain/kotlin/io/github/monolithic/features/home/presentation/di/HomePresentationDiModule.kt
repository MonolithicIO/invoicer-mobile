package io.github.monolithic.features.home.presentation.di

import io.github.monolithic.features.home.presentation.screens.home.HomeScreenModel
import io.github.monolithic.features.home.presentation.screens.home.tabs.settings.SettingsScreenModel
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTabScreenModel
import org.koin.dsl.module

val homePresentationDiModule = module {
    factory<SettingsScreenModel> {
        SettingsScreenModel(
            signOutService = get()
        )
    }

    factory<WelcomeTabScreenModel> {
        WelcomeTabScreenModel(
            session = get()
        )
    }

    factory {
        HomeScreenModel(
            session = get()
        )
    }
}
