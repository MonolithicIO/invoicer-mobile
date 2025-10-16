package io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_empty_description
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_empty_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_error_retry
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_new_invoice
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chveron_left
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsScreen
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.components.InvoiceListItem
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListCallbacks
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListEvent
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListMode
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListState
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.rememberInvoiceListCallbacks
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ErrorStateAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LoadingState
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.utils.compose.LazyListPaginationEffect
import io.github.monolithic.invoicer.foundation.utils.date.defaultFormat
import io.github.monolithic.invoicer.foundation.utils.events.EventEffect
import io.github.monolithic.invoicer.foundation.utils.money.moneyFormat
import io.github.monolithic.invoicer.foundation.watchers.bus.NewInvoiceEventBus
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.mp.KoinPlatform.getKoin

internal class InvoiceListScreen : Screen {

    companion object {
        const val SCREEN_KEY = "invoice-list-screen"
    }

    override val key: ScreenKey = SCREEN_KEY

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = koinScreenModel<InvoiceListScreenModel>()
        val state by viewModel.state.collectAsState()
        val newInvoiceEventBus = remember { getKoin().get<NewInvoiceEventBus>() }
        val snackBar = rememberInkSnackBarHostState()
        val scope = rememberCoroutineScope()

        val callbacks = rememberInvoiceListCallbacks(
            onClose = { navigator?.pop() },
            onRetry = { viewModel.loadPage() },
            onClickInvoice = {
                navigator?.push(InvoiceDetailsScreen(it))
            },
            onClickCreateInvoice = {
                navigator?.push(ScreenRegistry.get(InvoicerScreen.Invoices.Create))
            },
            onNextPage = { viewModel.nextPage() }
        )

        EventEffect(newInvoiceEventBus) {
            viewModel.loadPage(force = true)
        }

        LaunchedEffect(Unit) {
            viewModel.loadPage()
        }

        LaunchedEffect(viewModel.events) {
            viewModel.events.collectLatest {
                when (it) {
                    is InvoiceListEvent.Error -> {
                        scope.launch {
                            snackBar.showSnackBar(
                                message = it.message,
                            )
                        }
                    }
                }
            }
        }

        StateContent(
            callbacks = callbacks,
            state = state,
            snackbarHostState = snackBar
        )
    }

    @Composable
    fun StateContent(
        state: InvoiceListState,
        callbacks: InvoiceListCallbacks,
        snackbarHostState: InkSnackBarHostState
    ) {
        InkScaffold(
            topBar = {
                InkTopBar(
                    navigationIcon = painterResource(
                        resource = DsResources.drawable.ic_chveron_left
                    ),
                    onNavigationClick = callbacks::onClose,
                    title = stringResource(Res.string.invoice_list_title),
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                if (state.mode == InvoiceListMode.Content) {
                    InkPrimaryButton(
                        text = stringResource(Res.string.invoice_list_new_invoice),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(InkTheme.spacing.medium)
                            .navigationBarsPadding(),
                        onClick = callbacks::onCreateInvoiceClick
                    )
                }
            },
            snackBarHost = {
                InkSnackBarHost(snackbarHostState)
            },
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .fillMaxSize()
            ) {
                when (state.mode) {
                    InvoiceListMode.Content -> {
                        if (state.invoices.isEmpty()) {
                            EmptyState(
                                title = stringResource(Res.string.invoice_list_empty_title),
                                description = stringResource(Res.string.invoice_list_empty_description),
                                modifier = Modifier.fillMaxSize()
                            )
                        } else {
                            val listState = rememberLazyListState()

                            LazyListPaginationEffect(
                                state = listState,
                                enabled = true
                            ) {
                                callbacks.onNextPage()
                            }

                            LazyColumn(
                                modifier = Modifier.fillMaxSize(),
                                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium),
                                state = listState
                            ) {
                                itemsIndexed(
                                    items = state.invoices,
                                    key = { index, item -> item.id }
                                ) { index, invoiceItem ->
                                    InvoiceListItem(
                                        onClick = { callbacks.onClickInvoice(invoiceItem.id) },
                                        invoiceNumber = invoiceItem.externalId,
                                        customerName = invoiceItem.customerName,
                                        timeStamp = invoiceItem.createdAt.defaultFormat(),
                                        amount = invoiceItem.totalAmount.moneyFormat(),
                                        modifier = Modifier.fillMaxWidth(),
                                    )
                                }
                            }
                        }
                    }

                    InvoiceListMode.Loading -> LoadingState(
                        modifier = Modifier.fillMaxSize(),
                    )

                    InvoiceListMode.Error -> ErrorState(
                        modifier = Modifier.fillMaxSize(),
                        primaryAction = ErrorStateAction(
                            label = stringResource(Res.string.invoice_list_error_retry),
                            action = callbacks::onRetry
                        )
                    )
                }
            }
        }
    }
}
