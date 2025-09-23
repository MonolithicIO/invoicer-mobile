package io.github.monolithic.invoicer.features.auth.domain.model

internal sealed interface StartupDestination {
    object AuthMenu: StartupDestination
    object Home: StartupDestination
    object SelectCompany: StartupDestination
}