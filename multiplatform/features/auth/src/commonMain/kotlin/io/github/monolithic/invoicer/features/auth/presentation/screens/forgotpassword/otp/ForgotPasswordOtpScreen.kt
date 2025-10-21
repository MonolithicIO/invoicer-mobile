package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.otp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_cta
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_subtitle
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_title
import invoicer.multiplatform.features.auth.generated.resources.ic_danger_square
import io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.components.CloseForgotPasswordDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOtpInput
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

internal class ForgotPasswordOtpScreen(
    private val forgotPasswordRequestId: String
) : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ForgotPasswordOtpScreenModel>()
        val navigator = LocalNavigator.current
        val state by screenModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackBarState = rememberInkSnackBarHostState()
        val snackBarErrorIcon = painterResource(Res.drawable.ic_danger_square)
        var showExitDialog by remember { mutableStateOf(false) }

        BackHandler(enabled = true) {
            showExitDialog = true
        }

        FlowCollectEffect(
            flow = screenModel.events,
            key = screenModel
        ) { event ->
            when (event) {
                is ForgotPasswordOtpUiEvents.Failure -> scope.launch {
                    snackBarState.showSnackBar(
                        message = event.reason,
                        leadingIcon = snackBarErrorIcon
                    )
                }

                is ForgotPasswordOtpUiEvents.Success -> Unit
            }
        }

        StateContent(
            state = state,
            actions = remember {
                Actions(
                    onBack = {
                        showExitDialog = true
                    },
                    onSubmit = { screenModel.submit(requestId = forgotPasswordRequestId) },
                    onChangeOtpCode = screenModel::onChangeOtpCode,
                    onDismissDialog = {
                        showExitDialog = false
                    },
                    onAbandonOtp = {
                        showExitDialog = false
                        navigator?.pop()
                    },
                )
            },
            snackBarHostState = snackBarState,
            showExitDialog = showExitDialog
        )
    }

    @Composable
    fun StateContent(
        state: ForgotPasswordOtpState,
        showExitDialog: Boolean,
        actions: Actions,
        snackBarHostState: InkSnackBarHostState
    ) {
        InkScaffold(
            snackBarHost = {
                InkSnackBarHost(
                    state = snackBarHostState
                )
            },
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.forgot_password_otp_cta),
                    onClick = actions.onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .navigationBarsPadding()
                        .padding(InkTheme.spacing.medium),
                    enabled = state.isButtonEnabled,
                    loading = state.isLoading
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .verticalScroll(rememberScrollState())
            ) {
                Title(
                    title = stringResource(Res.string.forgot_password_otp_title),
                    subtitle = stringResource(Res.string.forgot_password_otp_subtitle)
                )
                VerticalSpacer(SpacerSize.XLarge3)
                InkOtpInput(
                    value = state.otpCode,
                    onValueChange = actions.onChangeOtpCode,
                    digitCount = 6,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        if (showExitDialog) {
            CloseForgotPasswordDialog(
                onCloseFlow = actions.onAbandonOtp,
                onDismissDialog = actions.onDismissDialog
            )
        }
    }

    data class Actions(
        val onBack: () -> Unit,
        val onSubmit: () -> Unit,
        val onChangeOtpCode: (String) -> Unit,
        val onDismissDialog: () -> Unit,
        val onAbandonOtp: () -> Unit
    )
}
