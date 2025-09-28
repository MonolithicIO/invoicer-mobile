package io.github.monolithic.invoicer.features.invoice.presentation.screens.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.customer.InvoiceCustomerScreen
import org.koin.mp.KoinPlatform

internal class CreateInvoiceFlow : Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        DisposableEffect(Unit) {
            val manager: CreateInvoiceFormManager = KoinPlatform.getKoin().get()

            onDispose {
                manager.closeScope()
            }
        }

        Navigator(
            screen = InvoiceCustomerScreen(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}
