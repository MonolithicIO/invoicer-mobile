package io.github.monolithic.invoicer.features.customer.presentation.screens.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
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
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_plus
import io.github.monolithic.invoicer.features.customer.presentation.screens.create.CreateCustomerScreen
import io.github.monolithic.invoicer.features.customer.presentation.screens.list.components.CustomerList
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkCircleButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class CustomerListScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CustomerListScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()

        val callBacks = remember {
            Actions(
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
            actions = callBacks
        )
    }

    @Composable
    fun StateContent(
        state: CustomerListState,
        actions: Actions
    ) {
        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    title = stringResource(Res.string.customer_list_title),
                    modifier = Modifier.statusBarsPadding()
                )
            },
            floatingActionButton = {
                if (state.mode == CustomerListMode.Content) {
                    InkCircleButton(
                        onClick = actions.onCreateNewCustomer,
                        icon = painterResource(DsResources.drawable.ic_plus)
                    )
                }
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
            ) {
                when (state.mode) {

                    CustomerListMode.Content -> CustomerList(
                        modifier = Modifier.fillMaxSize().navigationBarsPadding(),
                        items = state.customers
                    )

                    CustomerListMode.Error -> ErrorState(
                        modifier = Modifier.fillMaxSize(),
                        primaryAction = ErrorStateAction(
                            label = stringResource(Res.string.customer_list_retry),
                            action = actions.onRetry
                        ),
                        title = stringResource(Res.string.customer_list_error_title),
                        description = stringResource(Res.string.customer_list_error_description),
                    )

                    CustomerListMode.Loading -> LoadingState(Modifier.fillMaxSize())
                }
            }
        }
    }


    data class Actions(
        val onBack: () -> Unit,
        val requestNextPage: () -> Unit,
        val onCreateNewCustomer: () -> Unit,
        val onRetry: () -> Unit,
    )
}
