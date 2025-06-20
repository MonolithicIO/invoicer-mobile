package io.github.alaksion.invoicer.features.customer.presentation.screens.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.alaksion.invoicer.foundation.ui.FlowCollectEffect

internal class CustomerListScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CustomerListScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()

        val callBacks = remember {
            Callbacks(
                onBack = { navigator?.pop() },
                requestNextPage = screenModel::nextPage
            )
        }

        LaunchedEffect(Unit) {
            screenModel.loadPage()
        }

        FlowCollectEffect(
            screenModel.events, screenModel
        ) {
            when (it) {
                is CustomerListEvent.NextPageFailed -> {
                    // Handle next page failed event
                }
            }
        }

        StateContent(
            state = state,
            callbacks = callBacks
        )
    }

    @Composable
    fun StateContent(
        state: CustomerListState,
        callbacks: Callbacks
    ) {

    }


    data class Callbacks(
        val onBack: () -> Unit,
        val requestNextPage: () -> Unit,
    )
}