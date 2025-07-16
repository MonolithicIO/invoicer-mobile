package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Alarm
import androidx.compose.material.icons.outlined.AlarmOn
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.confirmation_activities_label
import invoicer.features.invoice.generated.resources.confirmation_amount_label
import invoicer.features.invoice.generated.resources.confirmation_company_name
import invoicer.features.invoice.generated.resources.confirmation_customer_name
import invoicer.features.invoice.generated.resources.confirmation_due_date_label
import invoicer.features.invoice.generated.resources.confirmation_issue_date_label
import invoicer.features.invoice.generated.resources.invoice_configuration_number_label
import invoicer.features.invoice.generated.resources.invoice_create_confirmation_continue_cta
import invoicer.features.invoice.generated.resources.invoice_create_confirmation_title
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.InvoiceActivityCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components.ConfirmationCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.feedback.InvoiceFeedbackScreen
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.tokens.Spacing
import io.github.monolithic.invoicer.foundation.utils.date.defaultFormat
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

internal class InvoiceConfirmationScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<InvoiceConfirmationScreenModel>()
        val state by screenModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val navigator = LocalNavigator.current

        LaunchedEffect(screenModel) { screenModel.initState() }

        LaunchedEffect(screenModel) {
            screenModel.events.collectLatest {
                when (it) {
                    is InvoiceConfirmationEvent.Error -> scope.launch {
                        snackbarHostState.showSnackbar(it.message)
                    }

                    InvoiceConfirmationEvent.Success -> navigator?.push(InvoiceFeedbackScreen())
                }
            }
        }

        StateContent(
            state = state,
            snackbarHostState = snackbarHostState,
            onBack = { navigator?.pop() },
            onSubmit = screenModel::submitInvoice
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: InvoiceConfirmationState,
        snackbarHostState: SnackbarHostState,
        onSubmit: () -> Unit,
        onBack: () -> Unit,
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.invoice_create_confirmation_title)
                        )
                    },
                    navigationIcon = {
                        BackButton(onBackClick = onBack)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium),
                    label = stringResource(Res.string.invoice_create_confirmation_continue_cta),
                    onClick = onSubmit,
                    isLoading = state.isLoading
                )
            },
            snackbarHost = {
                SnackbarHost(snackbarHostState)
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                ) {
                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.invoice_configuration_number_label),
                            content = state.invoiceNumber,
                            icon = Icons.Outlined.Badge
                        )
                    }
                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.confirmation_company_name),
                            content = state.companyName,
                            icon = Icons.Outlined.Business
                        )
                    }
                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.confirmation_customer_name),
                            content = state.customerName,
                            icon = Icons.Outlined.Business
                        )
                    }
                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.confirmation_issue_date_label),
                            content = state.issueDate.defaultFormat(),
                            icon = Icons.Outlined.Alarm
                        )
                    }
                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.confirmation_due_date_label),
                            content = state.dueDate.defaultFormat(),
                            icon = Icons.Outlined.AlarmOn
                        )
                    }

                    item {
                        ConfirmationCard(
                            label = stringResource(Res.string.confirmation_amount_label),
                            content = state.totalAmount,
                            icon = Icons.Outlined.AttachMoney
                        )
                    }

                    item {
                        HorizontalDivider()
                    }

                    item {
                        Text(
                            text = stringResource(Res.string.confirmation_activities_label),
                            style = MaterialTheme.typography.headlineMedium
                        )
                    }

                    items(
                        items = state.activities
                    ) {
                        InvoiceActivityCard(
                            modifier = Modifier.fillMaxWidth(),
                            quantity = it.quantity,
                            unitPrice = it.unitPrice,
                            description = it.description
                        )
                    }
                }
            }
        }
    }
}
