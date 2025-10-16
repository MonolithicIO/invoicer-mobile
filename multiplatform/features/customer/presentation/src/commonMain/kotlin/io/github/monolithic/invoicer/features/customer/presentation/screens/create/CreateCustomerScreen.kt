package io.github.monolithic.invoicer.features.customer.presentation.screens.create

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component3
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.customer.presentation.generated.resources.Res
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_cta
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_email_error
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_email_hint
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_name_error
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_name_hint
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_phone_error
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_phone_hint
import invoicer.multiplatform.features.customer.presentation.generated.resources.create_customer_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_edit
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_email
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_phone
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class CreateCustomerScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CreateCustomerScreenModel>()
        val navigator = LocalNavigator.current
        val scope = rememberCoroutineScope()

        val snackBarHostState = rememberInkSnackBarHostState()
        val state = screenModel.state.collectAsState()
        val callBacks = remember {
            Actions(
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
                    snackBarHostState.showSnackBar(
                        message = event.message
                    )
                }

                CreateCustomerEvent.Success -> navigator?.pop()
            }
        }

        StateContent(
            state = state.value,
            actions = callBacks,
            snackBarState = snackBarHostState
        )
    }

    @Composable
    fun StateContent(
        state: CreateCustomerState,
        actions: Actions,
        snackBarState: InkSnackBarHostState
    ) {
        val (nameRef, emailRef, phoneRef) = FocusRequester.createRefs()
        val keyBoard = LocalSoftwareKeyboardController.current

        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    title = stringResource(Res.string.create_customer_title),
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.create_customer_cta),
                    onClick = actions.onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                    loading = state.isButtonLoading,
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackBarState)
            }
        ) { scaffoldPadding ->
            val verticalsScroll = rememberScrollState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .verticalScroll(verticalsScroll)
            ) {

                InkOutlinedInput(
                    value = state.name,
                    onValueChange = actions.onNameChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(nameRef),
                    label = stringResource(Res.string.create_customer_name_hint),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { emailRef.requestFocus() }
                    ),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_edit),
                            contentDescription = null
                        )
                    },
                    isError = state.nameValid.not(),
                    maxLines = 1,
                    supportText = if (state.nameValid.not())
                        stringResource(Res.string.create_customer_name_error)
                    else null
                )

                VerticalSpacer(SpacerSize.Medium)

                InkOutlinedInput(
                    value = state.email,
                    onValueChange = actions.onEmailChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(emailRef),
                    label = stringResource(Res.string.create_customer_email_hint),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next
                    ),
                    keyboardActions = KeyboardActions(
                        onNext = { phoneRef.requestFocus() }
                    ),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_email),
                            contentDescription = null
                        )
                    },
                    isError = state.emailValid.not(),
                    maxLines = 1,
                    supportText = if (state.emailValid.not())
                        stringResource(Res.string.create_customer_email_error)
                    else null
                )

                VerticalSpacer(SpacerSize.Medium)

                InkOutlinedInput(
                    value = state.phone,
                    onValueChange = actions.onPhoneChange,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(phoneRef),
                    label = stringResource(Res.string.create_customer_phone_hint),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyBoard?.hide() }
                    ),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_phone),
                            contentDescription = null
                        )
                    },
                    isError = state.phoneValid.not(),
                    maxLines = 1,
                    supportText = if (state.phoneValid.not())
                        stringResource(Res.string.create_customer_phone_error)
                    else null
                )
            }
        }
    }

    data class Actions(
        val onNameChange: (String) -> Unit,
        val onEmailChange: (String) -> Unit,
        val onPhoneChange: (String) -> Unit,
        val onSubmit: () -> Unit,
        val onBack: () -> Unit,
    )
}
