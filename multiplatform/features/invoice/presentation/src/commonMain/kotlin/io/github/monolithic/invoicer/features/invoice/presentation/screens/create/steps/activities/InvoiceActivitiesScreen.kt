package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_empty
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_subtitle
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_continue_cta
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_plus
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceScreenTitle
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceToolbar
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components.AddActivityBottomSheet
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components.InvoiceActivityCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.rememberSnackMessages
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.InvoiceConfirmationScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkCircleButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.rememberInkBottomSheetState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class InvoiceActivitiesScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<InvoiceActivitiesScreenModel>()
        val navigator = LocalNavigator.current

        ScreenContent(
            screenModel = screenModel,
            onBack = { navigator?.pop() },
            onContinue = { navigator?.push(InvoiceConfirmationScreen()) }
        )
    }

    @Composable
    fun ScreenContent(
        screenModel: InvoiceActivitiesScreenModel,
        onBack: () -> Unit,
        onContinue: () -> Unit
    ) {
        val state by screenModel.state.collectAsState()
        val snackbarState = rememberInkSnackBarHostState()
        val messages = rememberSnackMessages()
        val scope = rememberCoroutineScope()

        LaunchedEffect(screenModel) {
            screenModel.initState()
        }

        LaunchedEffect(screenModel) {
            screenModel.events.collectLatest {
                when (it) {
                    InvoiceActivitiesEvent.ActivityQuantityError ->
                        scope.launch {
                            snackbarState.showSnackBar(message = messages.quantityError)
                        }

                    InvoiceActivitiesEvent.ActivityUnitPriceError -> scope.launch {
                        snackbarState.showSnackBar(message = messages.unitPriceError)
                    }
                }
            }
        }

        val callbacks = remember {
            Actions(
                onContinue = onContinue,
                onBack = onBack,
                onChangeDescription = screenModel::updateFormDescription,
                onChangeUnitPrice = screenModel::updateFormUnitPrice,
                onChangeQuantity = screenModel::updateFormQuantity,
                onClearForm = screenModel::clearForm,
                onAddActivity = screenModel::addActivity,
                onDelete = screenModel::removeActivity,
            )
        }

        StateContent(
            state = state,
            snackBarHostState = snackbarState,
            actions = callbacks
        )
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    fun StateContent(
        state: InvoiceActivitiesState,
        snackBarHostState: InkSnackBarHostState,
        actions: Actions
    ) {
        val sheetState = rememberInkBottomSheetState(
            skipPartiallyExpanded = true
        )

        var showSheet by remember {
            mutableStateOf(false)
        }
        val scope = rememberCoroutineScope()

        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                CreateInvoiceToolbar(
                    step = 3,
                    onBack = actions.onBack,
                    modifier = Modifier.statusBarsPadding(),
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium)
                        .navigationBarsPadding(),
                    text = stringResource(Res.string.invoice_create_continue_cta),
                    onClick = actions.onContinue,
                    enabled = state.isButtonEnabled
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackBarHostState)
            },
            floatingActionButton = {
                InkCircleButton(
                    onClick = { showSheet = true },
                    icon = painterResource(DsResources.drawable.ic_plus)
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                CreateInvoiceScreenTitle(
                    title = stringResource(Res.string.invoice_create_activity_title),
                    description = stringResource(Res.string.invoice_create_activity_subtitle)
                )

                VerticalSpacer(SpacerSize.Medium)

                AnimatedVisibility(
                    visible = state.activities.isEmpty(),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    EmptyState(
                        modifier = Modifier.fillMaxSize(),
                        description = stringResource(Res.string.invoice_create_activity_empty)
                    )
                }

                AnimatedVisibility(
                    visible = state.activities.isNotEmpty(),
                    modifier = Modifier.fillMaxSize(),
                ) {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                    ) {
                        items(
                            items = state.activities,
                            key = { it.id }
                        ) { activity ->
                            val animationModifier =
                                if (state.activities.size > 1) Modifier.animateItem()
                                else Modifier

                            InvoiceActivityCard(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .then(animationModifier),
                                item = activity,
                                onDeleteClick = { actions.onDelete(activity.id) }
                            )
                        }
                    }
                }
            }
        }

        AddActivityBottomSheet(
            isVisible = showSheet,
            sheetState = sheetState,
            formState = state.formState,
            onChangeQuantity = actions.onChangeQuantity,
            onChangeUnitPrice = actions.onChangeUnitPrice,
            onChangeDescription = actions.onChangeDescription,
            onDismiss = {
                showSheet = false
                actions.onClearForm()
            },
            onAddActivity = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showSheet = false
                    actions.onAddActivity()
                }
            },
            modifier = Modifier.systemBarsPadding()
        )
    }

    internal data class Actions(
        val onChangeDescription: (String) -> Unit,
        val onChangeUnitPrice: (String) -> Unit,
        val onChangeQuantity: (String) -> Unit,
        val onDelete: (String) -> Unit,
        val onClearForm: () -> Unit,
        val onAddActivity: () -> Unit,
        val onBack: () -> Unit,
        val onContinue: () -> Unit,
    )
}
