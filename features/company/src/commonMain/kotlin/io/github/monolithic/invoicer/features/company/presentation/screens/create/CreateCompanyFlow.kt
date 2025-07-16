package io.github.monolithic.invoicer.features.company.presentation.screens.create

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.NavigatorDisposeBehavior
import cafe.adriel.voyager.transitions.SlideTransition
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyFormManager
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.info.CompanyInfoStep
import org.koin.mp.KoinPlatform

internal class CreateCompanyFlow : Screen {

    @OptIn(ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {

        DisposableEffect(Unit) {
            val manager: CreateCompanyFormManager = KoinPlatform.getKoin().get()
            onDispose {
                manager.closeScope()
            }
        }

        Navigator(
            screen = CompanyInfoStep(),
            disposeBehavior = NavigatorDisposeBehavior(disposeSteps = false)
        ) { navigator ->
            SlideTransition(
                navigator = navigator,
                disposeScreenAfterTransitionEnd = true
            )
        }
    }
}
