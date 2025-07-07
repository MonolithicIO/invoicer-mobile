package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.address

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.create_company_address_city_label
import invoicer.features.company.generated.resources.create_company_address_line_1_label
import invoicer.features.company.generated.resources.create_company_address_line_2_help_text
import invoicer.features.company.generated.resources.create_company_address_line_2_label
import invoicer.features.company.generated.resources.create_company_address_postal_code_label
import invoicer.features.company.generated.resources.create_company_address_state_label
import invoicer.features.company.generated.resources.create_company_address_title
import invoicer.features.company.generated.resources.create_company_continue
import io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.payaccount.primary.PrimaryPayInfoScreen
import io.github.alaksion.invoicer.foundation.designSystem.components.InputField
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource


internal class CompanyAddressStep : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CompanyAddressScreenModel>()
        val navigator = LocalNavigator.current

        val state = screenModel.state.collectAsState()
        val callbacks = remember {
            Callbacks(
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
            state = state.value, callbacks = callbacks
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: CompanyAddressState,
        callbacks: Callbacks
    ) {
        val scrollState = rememberScrollState()

        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(text = stringResource(Res.string.create_company_address_title))
                    },
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBackClick)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.create_company_continue),
                    isEnabled = state.isButtonEnabled,
                    onClick = callbacks.onNextClick,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium)
                )
            },
            modifier = Modifier
                .systemBarsPadding()
                .imePadding()
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

                    InputField(
                        value = state.addressLine1,
                        onValueChange = callbacks.onAddressLine1Change,
                        label = {
                            Text(
                                text = stringResource(Res.string.create_company_address_line_1_label)
                            )
                        },
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
                    InputField(
                        value = state.addressLine2,
                        onValueChange = callbacks.onAddressLine2Change,
                        label = {
                            Text(
                                text = stringResource(Res.string.create_company_address_line_2_label)
                            )
                        },
                        supportingText = {
                            Text(
                                text = stringResource(Res.string.create_company_address_line_2_help_text)
                            )
                        },
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
                    InputField(
                        value = state.city,
                        onValueChange = callbacks.onCityChange,
                        label = {
                            Text(
                                text = stringResource(Res.string.create_company_address_city_label)
                            )
                        },
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
                    InputField(
                        value = state.state,
                        onValueChange = callbacks.onStateChange,
                        label = {
                            Text(
                                text = stringResource(Res.string.create_company_address_state_label)
                            )
                        },
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
                    InputField(
                        value = state.postalCode,
                        onValueChange = callbacks.onPostalCodeChange,
                        label = {
                            Text(
                                text = stringResource(
                                    Res.string.create_company_address_postal_code_label
                                )
                            )
                        },
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

    data class Callbacks(
        val onAddressLine1Change: (String) -> Unit,
        val onAddressLine2Change: (String) -> Unit,
        val onCityChange: (String) -> Unit,
        val onStateChange: (String) -> Unit,
        val onPostalCodeChange: (String) -> Unit,
        val onNextClick: () -> Unit,
        val onBackClick: () -> Unit
    )
}
