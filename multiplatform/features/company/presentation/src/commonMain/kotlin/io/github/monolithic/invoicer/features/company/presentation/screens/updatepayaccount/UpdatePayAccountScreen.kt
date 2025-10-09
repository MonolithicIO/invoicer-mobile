package io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount

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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component4
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.update_intermediary_account_title
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_bank_address_label
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_bank_name_label
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_cta
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_iban_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_swift_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.update_pay_account_title
import io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.components.DeletePayAccountModal
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

internal data class UpdatePayAccountScreen(
    val args: UpdatePayAccountScreenArgs
) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<UpdatePayAccountScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackBarHost = rememberInkSnackBarHostState()
        var showDeleteDialog by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            screenModel.initState(args)
        }

        FlowCollectEffect(
            flow = screenModel.event,
            key = Unit
        ) { event ->
            when (event) {
                is UpdatePayAccountEvent.Failure -> scope.launch {
                    snackBarHost.showSnackBar(
                        message = event.message,
                    )
                }

                UpdatePayAccountEvent.Success, UpdatePayAccountEvent.DeleteSuccess -> {
                    navigator?.pop()
                }
            }
        }

        StateContent(
            state = state,
            actions = remember {
                Actions(
                    onSwiftChange = screenModel::updateSwift,
                    onIbanChange = screenModel::updateIban,
                    onBankAddressChange = screenModel::updateBankAddress,
                    onBankNameChange = screenModel::updateBankName,
                    onUpdatePayAccount = { screenModel.updatePayAccount(args.payAccountId) },
                    onBackPressed = { navigator?.pop() },
                    onDelete = { showDeleteDialog = true },
                )
            },
            snackBarHostState = snackBarHost
        )

        DeletePayAccountModal(
            onDismissRequest = { showDeleteDialog = false },
            onConfirm = {
                showDeleteDialog = false
                screenModel.deletePayAccount(args.payAccountId)
            },
            isVisible = showDeleteDialog
        )

    }

    @Composable
    fun StateContent(
        state: UpdatePayAccountState,
        actions: Actions,
        snackBarHostState: InkSnackBarHostState
    ) {
        val keyboard = LocalSoftwareKeyboardController.current

        InkScaffold(
            snackBarHost = {
                InkSnackBarHost(
                    state = snackBarHostState
                )
            },
            topBar = {
                InkTopBar(
                    title = state.accountType.getText(),
                    onNavigationClick = actions.onBackPressed,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.update_pay_account_cta),
                    onClick = {
                        keyboard?.hide()
                        actions.onUpdatePayAccount()
                    },
                    enabled = state.isButtonEnabled,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium)
                        .navigationBarsPadding(),
                    loading = state.isButtonLoading
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            val verticalScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(verticalScroll),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                val (swiftFocus, ibanFocus, bankAddressFocus, bankNameFocus) = FocusRequester.createRefs()

                InkOutlinedInput(
                    value = state.swift,
                    onValueChange = actions.onSwiftChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(swiftFocus),
                    label = stringResource(Res.string.update_pay_account_swift_code_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { ibanFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.iban,
                    onValueChange = actions.onIbanChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(ibanFocus),
                    label = stringResource(Res.string.update_pay_account_iban_code_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { bankNameFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.bankName,
                    onValueChange = actions.onBankNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankNameFocus),
                    label = stringResource(Res.string.update_pay_account_bank_name_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { bankAddressFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.bankAddress,
                    onValueChange = actions.onBankAddressChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankAddressFocus),
                    label = stringResource(Res.string.update_pay_account_bank_address_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { keyboard?.hide() }
                    ),
                    maxLines = 1
                )
            }
        }
    }

    data class Actions(
        val onSwiftChange: (String) -> Unit,
        val onIbanChange: (String) -> Unit,
        val onBankAddressChange: (String) -> Unit,
        val onBankNameChange: (String) -> Unit,
        val onUpdatePayAccount: () -> Unit,
        val onBackPressed: () -> Unit,
        val onDelete: () -> Unit,
    )
}

@Serializable
internal data class UpdatePayAccountScreenArgs(
    val payAccountId: String,
    val swift: String,
    val iban: String,
    val bankAddress: String,
    val bankName: String,
    val accountType: AccountType
) {
    enum class AccountType {
        Primary,
        Intermediary;

        @Composable
        fun getText() = when (this) {
            Primary -> stringResource(Res.string.update_pay_account_title)
            Intermediary -> stringResource(Res.string.update_intermediary_account_title)
        }
    }
}
