package io.github.monolithic.invoicer.features.customer.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
import invoicer.multiplatform.features.customer.presentation.generated.resources.Res
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_error_description
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_error_title
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_retry
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_title
import io.github.monolithic.invoicer.features.customer.presentation.screens.create.CreateCustomerScreen
import io.github.monolithic.invoicer.features.customer.presentation.screens.list.components.CustomerList
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.LoadingState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.feedback.Feedback
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
import org.jetbrains.compose.resources.stringResource

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
                },
                onRetry = screenModel::loadPage
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
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.customer_list_title)
                        )
                    },
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
                when (state.mode) {

                    CustomerListMode.Content -> CustomerList(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        items = state.customers
                    )

                    CustomerListMode.Error -> Feedback(
                        modifier = Modifier.weight(1f).fillMaxWidth(),
                        primaryActionText = stringResource(Res.string.customer_list_retry),
                        onPrimaryAction = callbacks.onRetry,
                        icon = Icons.Outlined.ErrorOutline,
                        title = stringResource(Res.string.customer_list_error_title),
                        description = stringResource(Res.string.customer_list_error_description),
                    )

                    CustomerListMode.Loading -> LoadingState(Modifier.weight(1f).fillMaxWidth())
                }
            }
        }
    }


    data class Callbacks(
        val onBack: () -> Unit,
        val requestNextPage: () -> Unit,
        val onCreateNewCustomer: () -> Unit,
        val onRetry: () -> Unit,
    )
}
