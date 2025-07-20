package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.update_address_address_line_1_label
import invoicer.features.company.generated.resources.update_address_address_line_2_help_text
import invoicer.features.company.generated.resources.update_address_address_line_2_label
import invoicer.features.company.generated.resources.update_address_city_label
import invoicer.features.company.generated.resources.update_address_cta
import invoicer.features.company.generated.resources.update_address_postal_code_label
import invoicer.features.company.generated.resources.update_address_state_label
import invoicer.features.company.generated.resources.update_address_title
import io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress.components.UpdateAddressInput
import io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress.components.UpdateAddressInputIme
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.tokens.Spacing
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

internal class UpdateAddressScreen(
    private val args: Args
) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<UpdateAddressScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current
        val scope = rememberCoroutineScope()
        val snackBarHost = remember { SnackbarHostState() }

        LaunchedEffect(Unit) { screenModel.initState(args) }

        FlowCollectEffect(
            flow = screenModel.events,
            key = Unit
        ) { event ->
            when (event) {
                is UpdateAddressEvent.Error -> scope.launch {
                    snackBarHost.showSnackbar(event.message)
                }

                is UpdateAddressEvent.Success -> navigator?.pop()
            }
        }

        StateContent(
            state = state,
            callbacks = remember {
                Callbacks(
                    onAddressLineChange = screenModel::updateAddressLine,
                    onAddressLine2Change = screenModel::updateAddressLine2,
                    onCityChange = screenModel::updateCity,
                    onStateChange = screenModel::updateState,
                    onPostalCodeChange = screenModel::updatePostalCode,
                    onUpdateAddressClick = screenModel::submit,
                    onBackClick = { navigator?.pop() }
                )
            }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: UpdateAddressState,
        callbacks: Callbacks
    ) {
        Scaffold(
            modifier = Modifier.systemBarsPadding(),
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(Res.string.update_address_title)
                        )
                    },
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBackClick)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.update_address_cta),
                    onClick = callbacks.onUpdateAddressClick,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium),
                    isEnabled = state.isButtonEnabled,
                    isLoading = state.isButtonLoading
                )
            }
        ) { scaffoldPadding ->

            val keyboard = LocalSoftwareKeyboardController.current

            val (addressLineFocus,
                addressLine2Focus,
                cityFocus,
                stateFocus,
                postalCodeFocus) = FocusRequester.createRefs()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Spacing.medium)
                    .padding(scaffoldPadding),
                verticalArrangement = Arrangement.spacedBy(Spacing.medium)
            ) {
                UpdateAddressInput(
                    value = state.addressLine,
                    focusRef = addressLineFocus,
                    imeAction = UpdateAddressInputIme.Next,
                    onChange = callbacks.onAddressLineChange,
                    onImeAction = { addressLine2Focus.requestFocus() },
                    label = stringResource(Res.string.update_address_address_line_1_label)
                )

                UpdateAddressInput(
                    value = state.addressLine2.orEmpty(),
                    focusRef = addressLine2Focus,
                    imeAction = UpdateAddressInputIme.Next,
                    onChange = callbacks.onAddressLine2Change,
                    onImeAction = { cityFocus.requestFocus() },
                    label = stringResource(Res.string.update_address_address_line_2_label),
                    supportText = stringResource(Res.string.update_address_address_line_2_help_text)
                )

                UpdateAddressInput(
                    value = state.city,
                    focusRef = cityFocus,
                    imeAction = UpdateAddressInputIme.Next,
                    onChange = callbacks.onCityChange,
                    onImeAction = { stateFocus.requestFocus() },
                    label = stringResource(Res.string.update_address_city_label)
                )

                UpdateAddressInput(
                    value = state.state,
                    focusRef = stateFocus,
                    imeAction = UpdateAddressInputIme.Next,
                    onChange = callbacks.onStateChange,
                    onImeAction = { postalCodeFocus.requestFocus() },
                    label = stringResource(Res.string.update_address_state_label)
                )

                UpdateAddressInput(
                    value = state.postalCode,
                    focusRef = postalCodeFocus,
                    imeAction = UpdateAddressInputIme.Done,
                    onChange = callbacks.onPostalCodeChange,
                    onImeAction = { keyboard?.hide() },
                    label = stringResource(Res.string.update_address_postal_code_label)
                )
            }
        }
    }

    data class Callbacks(
        val onAddressLineChange: (String) -> Unit,
        val onAddressLine2Change: (String) -> Unit,
        val onCityChange: (String) -> Unit,
        val onStateChange: (String) -> Unit,
        val onPostalCodeChange: (String) -> Unit,
        val onUpdateAddressClick: () -> Unit,
        val onBackClick: () -> Unit,
    )

    @Serializable
    data class Args(
        val addressLine: String,
        val addressLine2: String?,
        val city: String,
        val state: String,
        val postalCode: String,
    )
}
