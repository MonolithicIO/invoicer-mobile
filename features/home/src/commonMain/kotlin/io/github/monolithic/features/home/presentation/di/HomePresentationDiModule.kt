package io.github.monolithic.features.home.presentation.di

import io.github.monolithic.features.home.presentation.tabs.settings.SettingsScreenModel
import io.github.monolithic.features.home.presentation.tabs.welcome.WelcomeTabScreenModel
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
}
