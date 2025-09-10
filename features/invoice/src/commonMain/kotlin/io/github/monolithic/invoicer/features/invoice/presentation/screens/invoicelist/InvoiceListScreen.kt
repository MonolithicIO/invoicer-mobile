package io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.registry.ScreenRegistry
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_list_empty_description
import invoicer.features.invoice.generated.resources.invoice_list_empty_title
import invoicer.features.invoice.generated.resources.invoice_list_error_description
import invoicer.features.invoice.generated.resources.invoice_list_error_retry
import invoicer.features.invoice.generated.resources.invoice_list_error_title
import invoicer.features.invoice.generated.resources.invoice_list_new_invoice
import invoicer.features.invoice.generated.resources.invoice_list_title
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsScreen
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.components.InvoiceListItem
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListCallbacks
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListEvent
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListMode
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListState
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.rememberInvoiceListCallbacks
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.CloseButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.feedback.Feedback
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.screenstate.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.navigation.InvoicerScreen
import io.github.monolithic.invoicer.foundation.ui.LazyListPaginationEffect
import io.github.monolithic.invoicer.foundation.ui.events.EventEffect
import io.github.monolithic.invoicer.foundation.watchers.NewInvoicePublisher
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
        val newInvoicePublisher = remember { getKoin().get<NewInvoicePublisher>() }
        val snackBar = remember { SnackbarHostState() }
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

        EventEffect(newInvoicePublisher) {
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
                            snackBar.showSnackbar(
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: InvoiceListState,
        callbacks: InvoiceListCallbacks,
        snackbarHostState: SnackbarHostState
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.invoice_list_title)
                        )
                    },
                    navigationIcon = { CloseButton(onBackClick = callbacks::onClose) },
                )
            },
            bottomBar = {
                if (state.mode == InvoiceListMode.Content) {
                    PrimaryButton(
                        label = stringResource(Res.string.invoice_list_new_invoice),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(Spacing.medium)
                    ) { callbacks.onCreateInvoiceClick() }
                }
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            },
            modifier = Modifier.systemBarsPadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
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
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(Spacing.medium),
                                state = listState
                            ) {
                                itemsIndexed(
                                    items = state.invoices,
                                    key = { index, item -> item.id }
                                ) { index, invoiceItem ->
                                    InvoiceListItem(
                                        item = invoiceItem,
                                        onClick = { callbacks.onClickInvoice(invoiceItem.id) }
                                    )
                                    if (index != state.invoices.lastIndex) {
                                        VerticalSpacer(SpacerSize.Medium)
                                        HorizontalDivider()
                                    }
                                }
                            }
                        }
                    }

                    InvoiceListMode.Loading -> Box(
                        modifier = Modifier
                            .fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator()
                    }

                    InvoiceListMode.Error -> Feedback(
                        modifier = Modifier
                            .fillMaxSize(),
                        primaryActionText = stringResource(Res.string.invoice_list_error_retry),
                        onPrimaryAction = callbacks::onRetry,
                        icon = Icons.Outlined.ErrorOutline,
                        title = stringResource(Res.string.invoice_list_error_title),
                        description = stringResource(Res.string.invoice_list_error_description)
                    )
                }
            }
        }
    }
}
