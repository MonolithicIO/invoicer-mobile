package io.github.alaksion.invoicer.features.customer.presentation.screens.create

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.customer.presentation.generated.resources.Res
import invoicer.features.customer.presentation.generated.resources.create_customer_cta
import invoicer.features.customer.presentation.generated.resources.create_customer_email_hint
import invoicer.features.customer.presentation.generated.resources.create_customer_name_hint
import invoicer.features.customer.presentation.generated.resources.create_customer_phone_hint
import invoicer.features.customer.presentation.generated.resources.create_customer_subtitle
import invoicer.features.customer.presentation.generated.resources.create_customer_title
import io.github.alaksion.invoicer.foundation.designSystem.components.InputField
import io.github.alaksion.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.alaksion.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.alaksion.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import io.github.alaksion.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

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
                onSubmit = screenModel::submit,
                onBack = { navigator?.pop() }
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

        StateContent(
            state = state.value,
            callbacks = callBacks
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        state: CreateCustomerState,
        callbacks: Callbacks
    ) {
        val (nameRef, emailRef, phoneRef) = FocusRequester.createRefs()
        val keyBoard = LocalSoftwareKeyboardController.current

        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBack)
                    }
                )
            },
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.create_customer_cta),
                    onClick = callbacks.onSubmit,
                    modifier = Modifier.fillMaxWidth().padding(Spacing.medium)
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
            ) {
                ScreenTitle(
                    title = stringResource(Res.string.create_customer_title),
                    subTitle = stringResource(Res.string.create_customer_subtitle)
                )
                VerticalSpacer(SpacerSize.XLarge3)

                InputField(
                    value = state.name,
                    onValueChange = callbacks.onNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(nameRef),
                    label = { Text(text = stringResource(Res.string.create_customer_name_hint)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { emailRef.requestFocus() }
                    )
                )

                VerticalSpacer(SpacerSize.Medium)

                InputField(
                    value = state.email,
                    onValueChange = callbacks.onEmailChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailRef),
                    label = { Text(text = stringResource(Res.string.create_customer_email_hint)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { phoneRef.requestFocus() }
                    )
                )

                VerticalSpacer(SpacerSize.Medium)

                InputField(
                    value = state.phone,
                    onValueChange = callbacks.onPhoneChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailRef),
                    label = { Text(text = stringResource(Res.string.create_customer_phone_hint)) },
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyBoard?.hide() }
                    )
                )
            }
        }
    }

    data class Callbacks(
        val onNameChange: (String) -> Unit,
        val onEmailChange: (String) -> Unit,
        val onPhoneChange: (String) -> Unit,
        val onSubmit: () -> Unit,
        val onBack: () -> Unit,
    )
}