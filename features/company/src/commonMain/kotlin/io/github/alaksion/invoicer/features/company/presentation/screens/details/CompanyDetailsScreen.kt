package io.github.alaksion.invoicer.features.company.presentation.screens.details

import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

internal class CompanyDetailsScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CompanyDetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            // Init
        }

        StateContent(
            state = state
        )
    }

    @Composable
    fun StateContent(
        state: CompanyDetailsState,
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding()
        ) {
        }
    }
}