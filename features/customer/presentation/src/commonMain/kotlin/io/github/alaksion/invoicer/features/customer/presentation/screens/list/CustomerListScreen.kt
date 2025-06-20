package io.github.alaksion.invoicer.features.customer.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.alaksion.invoicer.features.customer.presentation.screens.create.CreateCustomerScreen
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
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
                requestNextPage = screenModel::nextPage,
                onCreateNewCustomer = {
                    navigator?.push(CreateCustomerScreen())
                }
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: CustomerListState,
        callbacks: Callbacks
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBack)
                    }
                )
            },
            floatingActionButton = {
                if (state.mode == CustomerListMode.Content) {
                    FloatingActionButton(
                        onClick = callbacks.onCreateNewCustomer
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = null
                        )
                    }
                }
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                ScreenTitle(
                    title = "addw",
                    subTitle = "awdawd"
                )
                when (state.mode) {
                    CustomerListMode.Content -> TODO()
                    CustomerListMode.Error -> TODO()
                    CustomerListMode.Loading -> TODO()
                }
            }
        }
    }


    data class Callbacks(
        val onBack: () -> Unit,
        val requestNextPage: () -> Unit,
        val onCreateNewCustomer: () -> Unit
    )
}