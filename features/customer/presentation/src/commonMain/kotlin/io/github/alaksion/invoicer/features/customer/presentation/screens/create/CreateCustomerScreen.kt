package io.github.alaksion.invoicer.features.customer.presentation.screens.create

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import io.github.alaksion.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch

internal class CreateCustomerScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CreateCustomerScreenModel>()
        val navigator = LocalNavigator.current
        val scope = rememberCoroutineScope()

        val snackBarHostState = remember { SnackbarHostState() }
        val state = screenModel.state.collectAsState()
        val callBacks = remember {
            Callbacks(
                onNameChange = screenModel::updateName,
                onEmailChange = screenModel::updateEmail,
                onPhoneChange = screenModel::updatePhone,
                onSubmit = screenModel::submit
            )
        }

        FlowCollectEffect(
            screenModel.events,
            screenModel
        ) { event ->
            when (event) {
                is CreateCustomerEvent.Failure -> scope.launch {
                    snackBarHostState.showSnackbar(
                        message = event.message
                    )
                }

                CreateCustomerEvent.Success -> navigator?.pop()
            }
        }
    }

    @Composable
    fun StateContent(
        state: CreateCustomerState,
        callbacks: Callbacks
    ) {
        Scaffold { scaffoldPadding ->

        }
    }

    data class Callbacks(
        val onNameChange: (String) -> Unit,
        val onEmailChange: (String) -> Unit,
        val onPhoneChange: (String) -> Unit,
        val onSubmit: () -> Unit
    )
}