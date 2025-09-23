package io.github.monolithic.invoicer.features.auth.presentation.screens.startup

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.monolithic.invoicer.features.auth.domain.model.StartupDestination
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import io.github.monolithic.invoicer.foundation.navigation.extensions.getScreen
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect

class StartupScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<StartupScreenModel>()
        val navigator = LocalNavigator.current

        LaunchedEffect(viewModel) {
            viewModel.startApp()
        }

        FlowCollectEffect(
            flow = viewModel.events,
            viewModel
        ) { event ->
            val screen = when (event) {
                StartupDestination.AuthMenu -> {
                    getScreen(InvoicerScreen.Auth.AuthMenu)
                }

                StartupDestination.SelectCompany -> {
                    getScreen(
                        screen = InvoicerScreen.Company.SelectCompany(
                            intent = SelectCompanyIntent.StartupSelection
                        )
                    )
                }

                StartupDestination.Home -> {
                    getScreen(
                        screen = InvoicerScreen.Home
                    )
                }
            }
            navigator?.replaceAll(screen)
        }

        InkScaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it), contentAlignment = Alignment.Center
            ) {
                InkCircularIndicator()
            }
        }
    }
}
