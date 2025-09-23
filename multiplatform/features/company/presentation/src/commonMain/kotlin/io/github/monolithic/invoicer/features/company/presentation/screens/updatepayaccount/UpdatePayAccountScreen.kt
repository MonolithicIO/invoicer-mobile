package io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.InputField
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
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
        val snackBarHost = remember { SnackbarHostState() }
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
                    snackBarHost.showSnackbar(message = event.message)
                }

                UpdatePayAccountEvent.Success, UpdatePayAccountEvent.DeleteSuccess -> {
                    navigator?.pop()
                }
            }
        }

        StateContent(
            state = state,
            callbacks = remember {
                Callbacks(
                    onSwiftChange = screenModel::updateSwift,
                    onIbanChange = screenModel::updateIban,
                    onBankAddressChange = screenModel::updateBankAddress,
                    onBankNameChange = screenModel::updateBankName,
                    onUpdatePayAccount = { screenModel.updatePayAccount(args.payAccountId) },
                    onBackPressed = { navigator?.pop() },
                    onDelete = { showDeleteDialog = true }
                )
            }
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

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: UpdatePayAccountState,
        callbacks: Callbacks
    ) {
        val keyboard = LocalSoftwareKeyboardController.current

        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = state.accountType.getText()) },
                    navigationIcon = { BackButton(onBackClick = callbacks.onBackPressed) },
                    actions = {
                        if (state.isDeleteButtonVisible) {
                            IconButton(
                                onClick = callbacks.onDelete
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Delete,
                                    contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.update_pay_account_cta),
                    onClick = {
                        keyboard?.hide()
                        callbacks.onUpdatePayAccount()
                    },
                    isEnabled = state.isButtonEnabled,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium),
                    isLoading = state.isButtonLoading
                )
            },
            modifier = Modifier.imePadding().systemBarsPadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                val (swiftFocus, ibanFocus, bankAddressFocus, bankNameFocus) = FocusRequester.createRefs()

                InputField(
                    value = state.swift,
                    onValueChange = callbacks.onSwiftChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(swiftFocus),
                    label = {
                        Text(
                            text = stringResource(Res.string.update_pay_account_swift_code_label)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { ibanFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InputField(
                    value = state.iban,
                    onValueChange = callbacks.onIbanChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(ibanFocus),
                    label = {
                        Text(
                            text = stringResource(Res.string.update_pay_account_iban_code_label)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { bankNameFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InputField(
                    value = state.bankName,
                    onValueChange = callbacks.onBankNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankNameFocus),
                    label = {
                        Text(
                            text = stringResource(Res.string.update_pay_account_bank_name_label)
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { bankAddressFocus.requestFocus() }
                    ),
                    maxLines = 1
                )

                InputField(
                    value = state.bankAddress,
                    onValueChange = callbacks.onBankAddressChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankAddressFocus),
                    label = {
                        Text(
                            text = stringResource(Res.string.update_pay_account_bank_address_label)
                        )
                    },
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

    data class Callbacks(
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
