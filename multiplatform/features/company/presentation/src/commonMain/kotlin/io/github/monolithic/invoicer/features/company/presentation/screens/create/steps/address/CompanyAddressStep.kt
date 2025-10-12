package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.address

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component4
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component5
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_city_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_description
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_line_1_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_line_2_help_text
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_line_2_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_postal_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_state_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_address_title
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_continue
import io.github.monolithic.invoicer.features.company.presentation.screens.create.components.CreateCompanyTopBar
import io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount.primary.PrimaryPayInfoScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.stringResource


internal class CompanyAddressStep : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CompanyAddressScreenModel>()
        val navigator = LocalNavigator.current

        val state = screenModel.state.collectAsState()
        val action = remember {
            Action(
                onAddressLine1Change = screenModel::setAddressLine1,
                onAddressLine2Change = screenModel::setAddressLine2,
                onCityChange = screenModel::setCity,
                onStateChange = screenModel::setState,
                onPostalCodeChange = screenModel::setPostalCode,
                onNextClick = { navigator?.push(PrimaryPayInfoScreen()) },
                onBackClick = { navigator?.pop() })
        }

        LaunchedEffect(Unit) { screenModel.resumeState() }

        StateContent(
            state = state.value, action = action
        )
    }

    @Composable
    fun StateContent(
        state: CompanyAddressState,
        action: Action
    ) {
        val scrollState = rememberScrollState()

        InkScaffold(
            topBar = {
                CreateCompanyTopBar(
                    modifier = Modifier.statusBarsPadding(),
                    step = 2,
                    onBack = action.onBackClick
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.create_company_continue),
                    enabled = state.isButtonEnabled,
                    onClick = action.onNextClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium)
                        .navigationBarsPadding()
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .verticalScroll(scrollState)
            ) {
                Column(
                    verticalArrangement = Arrangement.spacedBy(Spacing.medium)
                ) {
                    val (addressLine1Focus, addressLine2Focus, cityFocus, stateFocus, postalCodeFocus) =
                        FocusRequester.createRefs()
                    val keyboard = LocalSoftwareKeyboardController.current

                    Title(
                        title = stringResource(Res.string.create_company_address_title),
                        subtitle = stringResource(Res.string.create_company_address_description)
                    )

                    VerticalSpacer(SpacerSize.Medium)

                    InkOutlinedInput(
                        value = state.addressLine1,
                        onValueChange = action.onAddressLine1Change,
                        label = stringResource(Res.string.create_company_address_line_1_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(addressLine1Focus),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { addressLine2Focus.requestFocus() }
                        )
                    )

                    InkOutlinedInput(
                        value = state.addressLine2,
                        onValueChange = action.onAddressLine2Change,
                        label = stringResource(Res.string.create_company_address_line_2_label),
                        supportText = stringResource(Res.string.create_company_address_line_2_help_text),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(addressLine2Focus),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { cityFocus.requestFocus() }
                        )
                    )

                    InkOutlinedInput(
                        value = state.city,
                        onValueChange = action.onCityChange,
                        label = stringResource(Res.string.create_company_address_city_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(cityFocus),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { stateFocus.requestFocus() }
                        )
                    )
                    InkOutlinedInput(
                        value = state.state,
                        onValueChange = action.onStateChange,
                        label = stringResource(Res.string.create_company_address_state_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(stateFocus),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = { postalCodeFocus.requestFocus() }
                        )
                    )
                    InkOutlinedInput(
                        value = state.postalCode,
                        onValueChange = action.onPostalCodeChange,
                        label = stringResource(Res.string.create_company_address_postal_code_label),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(postalCodeFocus),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Done
                        ),
                        keyboardActions = KeyboardActions(
                            onDone = { keyboard?.hide() }
                        )
                    )
                }
            }
        }
    }

    data class Action(
        val onAddressLine1Change: (String) -> Unit,
        val onAddressLine2Change: (String) -> Unit,
        val onCityChange: (String) -> Unit,
        val onStateChange: (String) -> Unit,
        val onPostalCodeChange: (String) -> Unit,
        val onNextClick: () -> Unit,
        val onBackClick: () -> Unit
    )
}
