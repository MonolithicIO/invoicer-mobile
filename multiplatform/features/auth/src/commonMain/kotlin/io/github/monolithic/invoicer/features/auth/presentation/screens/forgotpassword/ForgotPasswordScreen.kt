package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_cta
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_description
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_placeholder
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_email
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
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import io.github.monolithic.invoicer.foundation.utils.compose.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ForgotPasswordScreenModel>()
        val state by screenModel.state.collectAsState()
        val snackBarHost = rememberInkSnackBarHostState()
        val scope = rememberCoroutineScope()

        val navigator = LocalNavigator.current

        FlowCollectEffect(
            flow = screenModel.events,
            screenModel
        ) {
            when (it) {
                is ForgotPasswordUiEvents.Error -> scope.launch {
                    snackBarHost.showSnackBar(message = it.message)
                }

                ForgotPasswordUiEvents.Success -> {
                    // TODO: Navigate to OTP screen
                }
            }
        }

        StateContent(
            state = state,
            actions = remember {
                Actions(
                    onBack = { navigator?.pop() },
                    onChangeEmail = screenModel::updateEmail,
                    submit = screenModel::submit
                )
            },
            snackBarHostState = snackBarHost
        )
    }

    @Composable
    fun StateContent(
        actions: Actions,
        state: ForgotPasswordState,
        snackBarHostState: InkSnackBarHostState
    ) {
        InkScaffold(
            snackBarHost = {
                InkSnackBarHost(state = snackBarHostState)
            },
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.forgot_password_cta),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                    onClick = actions.submit,
                    enabled = state.buttonEnabled,
                    loading = state.isLoading
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(InkTheme.spacing.medium)
                    .padding(scaffoldPadding)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(
                    title = stringResource(Res.string.forgot_password_title),
                    subtitle = stringResource(Res.string.forgot_password_description)
                )
                VerticalSpacer(SpacerSize.XLarge3)
                InkOutlinedInput(
                    value = state.email,
                    onValueChange = actions.onChangeEmail,
                    modifier = Modifier.fillMaxWidth(),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_email),
                            contentDescription = null
                        )
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    singleLine = true,
                    placeholder = stringResource(Res.string.forgot_password_placeholder),
                    readOnly = state.isLoading
                )
            }
        }
    }

    data class Actions(
        val onBack: () -> Unit,
        val onChangeEmail: (String) -> Unit,
        val submit: () -> Unit
    )
}