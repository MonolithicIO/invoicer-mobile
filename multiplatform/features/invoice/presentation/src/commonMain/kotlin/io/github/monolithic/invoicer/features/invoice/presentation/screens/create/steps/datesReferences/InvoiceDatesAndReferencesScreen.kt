package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.datesReferences

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_continue_cta
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_date_placeholder
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_description
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_due_date_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_id_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_id_placeholder
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_id_support
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_issue_date_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_date_reference_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_calendar
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_key
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceToolbar
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.InvoiceActivitiesScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import io.github.monolithic.invoicer.foundation.utils.transformations.MaskVisualTransformation
import io.github.monolithic.invoicer.foundation.utils.transformations.MaskVisualTransformation.Companion.DATE_MASK
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class InvoiceDatesAndReferencesScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<InvoiceDatesAndReferencesScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        val callBacks = remember {
            Callbacks(
                onBack = { navigator?.pop() },
                onNext = {
                    screenModel.saveConfiguration()
                },
                onInvoiceIdChange = screenModel::updateInvoiceNumber,
                onDueDateChange = screenModel::updateInvoiceDueDate,
                onIssueDateChange = screenModel::updateIssueDate
            )
        }

        LaunchedEffect(screenModel) { screenModel.refreshState() }

        FlowCollectEffect(
            flow = screenModel.events,
            screenModel
        ) {
            when (it) {
                InvoiceDatesAndReferencesUiEvents.Continue -> {
                    navigator?.push(InvoiceActivitiesScreen())
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
        state: InvoiceDatesAndReferencesState,
        callbacks: Callbacks
    ) {
        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                CreateInvoiceToolbar(
                    step = 2,
                    onBack = callbacks.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.invoice_create_continue_cta),
                    onClick = callbacks.onNext,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium)
                        .navigationBarsPadding(),
                    enabled = state.isButtonEnabled
                )
            }
        ) { scaffoldPadding ->
            val scrollState = rememberScrollState()
            val keyboard = LocalSoftwareKeyboardController.current
            val (dueDateRef, issueDateRef, invoiceIdRef) = FocusRequester.createRefs()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                Title(
                    title = stringResource(Res.string.invoice_date_reference_title),
                    subtitle = stringResource(Res.string.invoice_date_reference_description)
                )

                InkOutlinedInput(
                    label =
                        stringResource(Res.string.invoice_date_reference_issue_date_label),
                    placeholder =
                        stringResource(Res.string.invoice_date_reference_date_placeholder),
                    value = state.issueDate,
                    onValueChange = { updatedValue ->
                        if (updatedValue.length <= 8) {
                            callbacks.onIssueDateChange(updatedValue)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(issueDateRef),
                    keyboardActions = KeyboardActions(
                        onNext = { dueDateRef.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    singleLine = true,
                    isError = state.issueDateState != DateState.Valid,
                    supportText = state.issueDateState.text(),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_calendar),
                            contentDescription = null,
                            tint =
                                if (state.issueDateState == DateState.Valid)
                                    InkTheme.colorScheme.onBackground
                                else InkTheme.colorScheme.error
                        )
                    },
                    visualTransformation = MaskVisualTransformation(mask = DATE_MASK),
                )


                InkOutlinedInput(
                    label =
                        stringResource(Res.string.invoice_date_reference_due_date_label),
                    placeholder =
                        stringResource(Res.string.invoice_date_reference_date_placeholder),
                    value = state.dueDate,
                    onValueChange = { updatedValue ->
                        if (updatedValue.length <= 8) {
                            callbacks.onDueDateChange(updatedValue)
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(dueDateRef),
                    keyboardActions = KeyboardActions(
                        onNext = { invoiceIdRef.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_calendar),
                            contentDescription = null,
                            tint =
                                if (state.dueDateState == DateState.Valid)
                                    InkTheme.colorScheme.onBackground
                                else InkTheme.colorScheme.error
                        )
                    },
                    visualTransformation = MaskVisualTransformation(mask = DATE_MASK),
                    singleLine = true,
                    isError = state.dueDateState != DateState.Valid,
                    supportText = state.dueDateState.text()
                )

                InkOutlinedInput(
                    label = stringResource(Res.string.invoice_date_reference_id_label),
                    placeholder = stringResource(Res.string.invoice_date_reference_id_placeholder),
                    supportText = stringResource(Res.string.invoice_date_reference_id_support),
                    value = state.invoiceId,
                    onValueChange = callbacks.onInvoiceIdChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(invoiceIdRef),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboard?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    singleLine = true,
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_key),
                            contentDescription = null,
                            tint = InkTheme.colorScheme.onBackground
                        )
                    }
                )
            }
        }
    }

    data class Callbacks(
        val onBack: () -> Unit,
        val onNext: () -> Unit,
        val onInvoiceIdChange: (String) -> Unit,
        val onDueDateChange: (String) -> Unit,
        val onIssueDateChange: (String) -> Unit,
    )
}
