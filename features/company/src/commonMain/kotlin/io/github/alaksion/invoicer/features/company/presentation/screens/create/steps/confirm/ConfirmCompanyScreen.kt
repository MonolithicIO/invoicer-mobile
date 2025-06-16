package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

internal class ConfirmCompanyScreen: Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ConfirmCompanyScreenModel>()
        val state = screenModel.state.collectAsState()
    }

}