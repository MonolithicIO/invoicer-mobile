package io.github.monolithic.invoicer.sharedApp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InkTheme
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import io.github.monolithic.invoicer.foundation.watchers.AuthEvent
import io.github.monolithic.invoicer.foundation.watchers.AuthEventBus
import org.koin.mp.KoinPlatform

@OptIn(ExperimentalVoyagerApi::class)
@Composable
fun InvoicerApp() {
    val authEventBus: AuthEventBus = remember { KoinPlatform.getKoin().get() }

    InkTheme {
        Navigator(
            screen = ScreenRegistry.get(InvoicerScreen.Auth.Startup),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )

            AuthEventEffect(
                bus = authEventBus,
                onSignIn = {
                    navigator.replaceAll(
                        ScreenRegistry.get(
                            InvoicerScreen.Company.SelectCompany(
                                intent = SelectCompanyIntent.StartupSelection
                            )
                        )
                    )
                },
                onSignOff = {
                    navigator.replaceAll(
                        ScreenRegistry.get(InvoicerScreen.Auth.AuthMenu)
                    )
                }
            )
        }
    }
}

@Composable
private fun AuthEventEffect(
    bus: AuthEventBus,
    onSignIn: () -> Unit,
    onSignOff: () -> Unit
) {
    LaunchedEffect(Unit) {
        bus.subscribe().collect {
            when (it) {
                AuthEvent.SignedIn -> onSignIn()
                AuthEvent.SignedOut -> onSignOff()
            }
        }
    }
}
