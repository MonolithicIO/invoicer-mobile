package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_add_cta
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_subtitle
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_title
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_continue_cta
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceScreenTitle
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceToolbar
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.InvoiceActivityCard
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components.AddActivityBottomSheet
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.rememberSnackMessages
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.InvoiceConfirmationScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.sheets.modal.props.rememberInkBottomSheetState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.SwipeableCard
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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
            callbacks = callbacks
        )
    }

    @OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
    @Composable
    fun StateContent(
        state: InvoiceActivitiesState,
        snackBarHostState: InkSnackBarHostState,
        callbacks: Actions
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
                    onBack = callbacks.onBack,
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
                    onClick = callbacks.onContinue,
                    enabled = state.isButtonEnabled
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackBarHostState)
            }
        ) { scaffoldPadding ->
            val verticalScroll = rememberScrollState()
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(verticalScroll)
            ) {
                CreateInvoiceScreenTitle(
                    title = stringResource(Res.string.invoice_create_activity_title),
                    description = stringResource(Res.string.invoice_create_activity_subtitle)
                )

                VerticalSpacer(SpacerSize.Medium)

                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .testTag(TestTags.LIST),
                    verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                ) {
                    stickyHeader {
                        PrimaryButton(
                            label = stringResource(Res.string.invoice_create_activity_add_cta),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag(ADD_ACTIVITY),
                            onClick = {
                                showSheet = true
                            }
                        )
                    }

                    items(
                        items = state.activities,
                        key = { it.id }
                    ) { activity ->
                        var isRevealed by remember { mutableStateOf(false) }
                        SwipeableCard(
                            content = {
                                InvoiceActivityCard(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .testTag(LIST_ITEM),
                                    quantity = activity.quantity,
                                    description = activity.description,
                                    unitPrice = activity.unitPrice,
                                )
                            },
                            extraContent = {
                                Box(
                                    modifier = Modifier
                                        .fillMaxHeight()
                                        .padding(Spacing.medium),
                                    contentAlignment = Alignment.Center
                                ) {
                                    IconButton(
                                        onClick = {
                                            callbacks.onDelete(activity.id)
                                        }
                                    ) {
                                        Icon(
                                            imageVector = Icons.Outlined.Delete,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.error
                                        )
                                    }
                                }
                            },
                            onExpanded = { isRevealed = true },
                            onCollapsed = { isRevealed = false },
                            modifier = Modifier.fillMaxWidth(),
                            isRevealed = isRevealed
                        )
                    }
                }
            }
        }

        AddActivityBottomSheet(
            isVisible = showSheet,
            sheetState = sheetState,
            formState = state.formState,
            onChangeQuantity = callbacks.onChangeQuantity,
            onChangeUnitPrice = callbacks.onChangeUnitPrice,
            onChangeDescription = callbacks.onChangeDescription,
            onDismiss = {
                showSheet = false
                callbacks.onClearForm()
            },
            onAddActivity = {
                scope.launch {
                    sheetState.hide()
                }.invokeOnCompletion {
                    showSheet = false
                    callbacks.onAddActivity()
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

    companion object TestTags {
        const val LIST = "add_invoice_activity_list"
        const val ADD_ACTIVITY = "add_invoice_activity_add"
        const val LIST_ITEM = "add_invoice_activity_item"
    }
}
