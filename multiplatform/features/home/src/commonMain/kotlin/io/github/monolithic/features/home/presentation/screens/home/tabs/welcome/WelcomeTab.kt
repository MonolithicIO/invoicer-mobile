package io.github.monolithic.features.home.presentation.screens.home.tabs.welcome

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.home_retry
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.components.LatestInvoicesCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent
import org.jetbrains.compose.resources.stringResource

internal object WelcomeTab : Tab {

    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 1u,
            title = "",
            icon = null
        )

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<WelcomeTabScreenModel>()
        val state = screenModel.state.collectAsState()
        val navigator = LocalNavigator.current?.parent

        val callbacks = remember {
            WelcomeActions(
                onAddInvoiceClick = {
                    navigator?.push(
                        ScreenRegistry.get(InvoicerScreen.Invoices.Create)
                    )
                },
                onCustomerClick = {
                    navigator?.push(ScreenRegistry.get(InvoicerScreen.Customer.List))
                },
                onChangeCompanyClick = {
                    navigator?.push(
                        ScreenRegistry.get(
                            InvoicerScreen.Company.SelectCompany(
                                SelectCompanyIntent.ChangeCompany
                            )
                        )
                    )
                },
                onViewInvoicesClick = {
                    navigator?.push(
                        ScreenRegistry.get(
                            InvoicerScreen.Invoices.List
                        )
                    )
                },
                onRetry = { screenModel.loadData(isRetry = true) }
            )
        }

        LaunchedEffect(Unit) {
            screenModel.loadData()
        }

        StateContent(
            actions = callbacks,
            state = state.value
        )
    }

    @Composable
    fun StateContent(
        state: WelcomeTabState,
        actions: WelcomeActions
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(InkTheme.colorScheme.surfaceLight)
                .padding(InkTheme.spacing.medium)
        ) {
            when (state.mode) {
                WelcomeTabMode.Loading -> LoadingState(
                    modifier = Modifier.fillMaxSize()
                )

                WelcomeTabMode.Error -> ErrorState(
                    modifier = Modifier.fillMaxSize(),
                    primaryAction = ErrorStateAction(
                        label = stringResource(Res.string.home_retry),
                        action = actions.onRetry
                    )
                )

                WelcomeTabMode.Content -> SuccessContent(
                    modifier = Modifier.fillMaxSize(),
                    state = state,
                    actions = actions
                )
            }
        }
    }

    @Composable
    fun SuccessContent(
        state: WelcomeTabState,
        actions: WelcomeActions,
        modifier: Modifier = Modifier,
    ) {
        Column(modifier = modifier) {
            LatestInvoicesCard(
                items = state.latestInvoices,
                modifier = Modifier.fillMaxWidth(),
                onViewAllClick = actions.onViewInvoicesClick,
                onCreateInvoiceClick = actions.onAddInvoiceClick
            )
        }
    }

    data class WelcomeActions(
        val onAddInvoiceClick: () -> Unit,
        val onCustomerClick: () -> Unit,
        val onChangeCompanyClick: () -> Unit,
        val onViewInvoicesClick: () -> Unit,
        val onRetry: () -> Unit,
    )
}
