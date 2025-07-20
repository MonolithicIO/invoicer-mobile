package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch
import kotlinx.serialization.Serializable

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

    @Composable
    fun StateContent(
        state: UpdateAddressState,
        callbacks: Callbacks
    ) {
        Scaffold {

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
        val addressLine: String = "",
        val addressLine2: String? = "",
        val city: String = "",
        val state: String = "",
        val postalCode: String = "",
    )
}