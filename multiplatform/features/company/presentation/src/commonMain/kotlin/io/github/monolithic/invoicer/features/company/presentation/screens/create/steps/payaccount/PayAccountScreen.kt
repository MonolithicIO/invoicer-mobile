package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount

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
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_continue
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_description
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_info_bank_address_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_info_bank_name_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_info_iban_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_info_primary_use_intermediary
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_info_swift_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_pay_title
import io.github.monolithic.invoicer.features.company.presentation.screens.create.components.CreateCompanyTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ToggleListItem
import org.jetbrains.compose.resources.stringResource

internal class PayAccountScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<PayAccountScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current

        LaunchedEffect(Unit) {
            screenModel.resumeState()
        }

        StateContent(
            actions = remember(screenModel) {
                Actions(
                    onBack = { navigator?.pop() },
                    onContinue = {},
                    onUpdatePrimarySwift = screenModel::onChangePrimarySwift,
                    onUpdatePrimaryIban = screenModel::onChangePrimaryIban,
                    onUpdatePrimaryBankName = screenModel::onChangePrimaryBankName,
                    onUpdatePrimaryBankAddress = screenModel::onChangePrimaryBankAddress,
                    onToggleUseIntermediaryAccount = screenModel::toggleIntermediaryAccount
                )
            },
            state = state
        )
    }

    @Composable
    fun StateContent(
        actions: Actions,
        state: PayAccountState
    ) {
        InkScaffold(
            topBar = {
                CreateCompanyTopBar(
                    onBack = actions.onBack,
                    step = 3,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.create_company_continue),
                    enabled = state.isButtonEnabled,
                    onClick = actions.onContinue,
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(InkTheme.spacing.medium)
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->

            val scrollState = rememberScrollState()

            val (swiftRef, ibanRef) = FocusRequester.createRefs()
            val (bankNameRef, bankAddressRef) = FocusRequester.createRefs()
            val keyboardController = LocalSoftwareKeyboardController.current

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.small)
                    .verticalScroll(scrollState),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                Title(
                    title = stringResource(Res.string.create_company_pay_title),
                    subtitle = stringResource(Res.string.create_company_pay_description)
                )

                InkOutlinedInput(
                    value = state.primarySwift,
                    onValueChange = actions.onUpdatePrimarySwift,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(swiftRef),
                    keyboardActions = KeyboardActions(
                        onNext = { ibanRef.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Next
                    ),
                    label = stringResource(Res.string.create_company_pay_info_swift_code_label),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.primaryIban,
                    onValueChange = actions.onUpdatePrimaryIban,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(ibanRef),
                    keyboardActions = KeyboardActions(
                        onNext = { bankNameRef.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        capitalization = KeyboardCapitalization.Characters,
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Next
                    ),
                    label = stringResource(Res.string.create_company_pay_info_iban_code_label),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.primaryBankName,
                    onValueChange = actions.onUpdatePrimaryBankName,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankNameRef),
                    keyboardActions = KeyboardActions(
                        onNext = { bankAddressRef.requestFocus() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Next
                    ),
                    label = stringResource(Res.string.create_company_pay_info_bank_name_label),
                    maxLines = 1
                )

                InkOutlinedInput(
                    value = state.primaryBankAddress,
                    onValueChange = actions.onUpdatePrimaryBankAddress,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(bankAddressRef),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboardController?.hide() }
                    ),
                    keyboardOptions = KeyboardOptions(
                        autoCorrectEnabled = false,
                        imeAction = ImeAction.Done
                    ),
                    label = stringResource(Res.string.create_company_pay_info_bank_address_label),
                    maxLines = 1
                )

                ToggleListItem(
                    label = stringResource(Res.string.create_company_pay_info_primary_use_intermediary),
                    checked = state.useIntermediaryAccount,
                    onCheckedChange = actions.onToggleUseIntermediaryAccount,
                    labelWeight = FontWeight.SemiBold
                )
            }
        }

    }

    data class Actions(
        val onBack: () -> Unit,
        val onContinue: () -> Unit,
        val onUpdatePrimarySwift: (String) -> Unit,
        val onUpdatePrimaryIban: (String) -> Unit,
        val onUpdatePrimaryBankName: (String) -> Unit,
        val onUpdatePrimaryBankAddress: (String) -> Unit,
        val onToggleUseIntermediaryAccount: (Boolean) -> Unit,
    )
}