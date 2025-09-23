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
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold

class StartupScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<StartupScreenModel>()
        LaunchedEffect(viewModel) { viewModel.startApp() }

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
