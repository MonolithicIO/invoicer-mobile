package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.confirmation_activities_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.confirmation_due_date_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.confirmation_issue_date_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_confirmation_continue_cta
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_confirmation_subtitle
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_confirmation_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_number
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceToolbar
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components.InvoiceActivityCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components.AmountConfirmationCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components.ConfirmationCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components.ConfirmationHeader
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.feedback.InvoiceFeedbackScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
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
        val snackbarHostState = rememberInkSnackBarHostState()
        val navigator = LocalNavigator.current

        LaunchedEffect(screenModel) { screenModel.initState() }

        LaunchedEffect(screenModel) {
            screenModel.events.collectLatest {
                when (it) {
                    is InvoiceConfirmationEvent.Error -> scope.launch {
                        snackbarHostState.showSnackBar(it.message)
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

    @Composable
    fun StateContent(
        state: InvoiceConfirmationState,
        snackbarHostState: InkSnackBarHostState,
        onSubmit: () -> Unit,
        onBack: () -> Unit,
    ) {
        val scrollState = rememberScrollState()

        InkScaffold(
            topBar = {
                CreateInvoiceToolbar(
                    modifier = Modifier.fillMaxWidth().statusBarsPadding(),
                    onBack = onBack,
                    step = 4
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium)
                        .navigationBarsPadding(),
                    text = stringResource(Res.string.invoice_create_confirmation_continue_cta),
                    onClick = onSubmit,
                    loading = state.isLoading
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackbarHostState)
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                Title(
                    title = stringResource(Res.string.invoice_create_confirmation_title),
                    subtitle = stringResource(Res.string.invoice_create_confirmation_subtitle),
                    modifier = Modifier.fillMaxWidth()
                )

                VerticalSpacer(SpacerSize.Medium)

                ConfirmationHeader(
                    customerName = state.customerName,
                    modifier = Modifier.fillMaxWidth()

                )

                InkHorizontalDivider()

                ConfirmationCard(
                    label = stringResource(Res.string.confirmation_issue_date_label),
                    content = state.issueDate.defaultFormat(),
                    modifier = Modifier.fillMaxWidth()
                )

                ConfirmationCard(
                    label = stringResource(Res.string.confirmation_due_date_label),
                    content = state.dueDate.defaultFormat(),
                    modifier = Modifier.fillMaxWidth()
                )

                ConfirmationCard(
                    label = stringResource(Res.string.invoice_details_number),
                    content = state.invoiceNumber,
                    modifier = Modifier.fillMaxWidth()
                )

                InkHorizontalDivider()

                AmountConfirmationCard(
                    amount = state.totalAmount,
                    modifier = Modifier.fillMaxWidth()
                )

                InkHorizontalDivider()

                InkText(
                    text = stringResource(Res.string.confirmation_activities_label),
                    style = InkTextStyle.Heading4,
                    weight = FontWeight.SemiBold
                )
                state.activities.forEach { activity ->
                    InvoiceActivityCard(
                        item = activity,
                        onDeleteClick = null,
                        modifier = Modifier.fillMaxWidth(),
                    )
                }
            }
        }
    }
}
