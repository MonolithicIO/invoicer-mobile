package io.github.monolithic.invoicer.features.auth.presentation.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
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
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_duplicate_account_cta
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_duplicate_account_description
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_duplicate_account_title
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_error
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_have_account_prefix
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_have_account_suffix
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_submit_button
import invoicer.features.auth.presentation.generated.resources.ic_danger_square
import invoicer.foundation.design_system.generated.resources.ic_chveron_left
import invoicer.foundation.design_system.generated.resources.img_error_default
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components.SignUpForm
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components.SignUpHeader
import io.github.monolithic.invoicer.features.auth.presentation.screens.signupfeedback.SignUpFeedbackScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkTextButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.InkDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarBottomPadding
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import invoicer.foundation.design_system.generated.resources.Res as DsRes

internal class SignUpScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = koinScreenModel<SignUpScreenModel>()
        val state by viewModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val genericErrorMessage = stringResource(Res.string.auth_sign_up_error)
        val keyboard = LocalSoftwareKeyboardController.current
        var showDuplicateAccountDialog by remember { mutableStateOf(false) }

        val errorSnackBarPainter = painterResource(Res.drawable.ic_danger_square)
        val snackBarState = rememberInkSnackBarHostState()

        FlowCollectEffect(
            flow = viewModel.events,
            key = viewModel
        ) {
            when (it) {
                SignUpEvents.Success -> navigator?.push(SignUpFeedbackScreen())

                is SignUpEvents.Failure -> {
                    scope.launch {
                        snackBarState.showSnackBar(
                            message = it.message,
                            leadingIcon = errorSnackBarPainter
                        )
                    }
                }

                SignUpEvents.GenericFailure -> scope.launch {
                    snackBarState.showSnackBar(
                        message = genericErrorMessage,
                        leadingIcon = errorSnackBarPainter
                    )
                }

                SignUpEvents.DuplicateAccount -> showDuplicateAccountDialog = true
            }
        }

        val callBacks = remember {
            SignUpCallbacks(
                onBackClick = { navigator?.pop() },
                onSubmitClick = {
                    keyboard?.hide()
                    viewModel.createAccount()
                },
                onEmailChange = viewModel::onEmailChange,
                onPasswordChange = viewModel::onPasswordChange,
                toggleCensorship = viewModel::toggleCensorship,
                onDismissDialog = {
                    showDuplicateAccountDialog = false
                },
                onSignInClick = { navigator?.replaceAll(LoginScreen()) },
            )
        }

        StateContent(
            state = state,
            snackBarState = snackBarState,
            showDuplicateAccountDialog = showDuplicateAccountDialog,
            callbacks = callBacks
        )
    }

    @Composable
    fun StateContent(
        state: SignUpScreenState,
        snackBarState: InkSnackBarHostState,
        callbacks: SignUpCallbacks,
        showDuplicateAccountDialog: Boolean,
    ) {
        val scrollState = rememberScrollState()
        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                InkTopBar(
                    modifier = Modifier.statusBarsPadding(),
                    navigationIcon = painterResource(DsRes.drawable.ic_chveron_left),
                    onNavigationClick = callbacks.onBackClick,
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackBarState)
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.auth_sign_up_submit_button),
                    enabled = state.buttonEnabled,
                    loading = state.requestLoading,
                    onClick = callbacks.onSubmitClick,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = InkTheme.spacing.medium)
                        .systemBarBottomPadding()
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                SignUpHeader()
                VerticalSpacer(height = SpacerSize.XLarge3)
                SignUpForm(
                    modifier = Modifier.fillMaxWidth(),
                    state = state,
                    onPasswordChange = callbacks.onPasswordChange,
                    onEmailChange = callbacks.onEmailChange,
                    toggleCensorship = callbacks.toggleCensorship
                )
                VerticalSpacer(height = SpacerSize.XLarge3)
                InkTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = callbacks.onSignInClick,
                    text = buildAnnotatedString {
                        withStyle(
                            style = InkTheme.typography.bodyXLarge.copy(
                                color = InkTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Normal
                            ).toSpanStyle(),
                        ) {
                            append(text = stringResource(Res.string.auth_sign_up_have_account_prefix))
                        }
                        append(" ")
                        withStyle(
                            style = InkTheme.typography.bodyXLarge.copy(
                                color = InkTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            ).toSpanStyle(),
                        ) {
                            append(text = stringResource(Res.string.auth_sign_up_have_account_suffix))
                        }
                    }
                )
            }

            if (showDuplicateAccountDialog) {
                InkDialog(
                    onDismissRequest = callbacks.onDismissDialog,
                    title = stringResource(Res.string.auth_sign_up_duplicate_account_title),
                    description = stringResource(
                        Res.string.auth_sign_up_duplicate_account_description,
                        state.email
                    ),
                    primaryAction = InkDialogAction(
                        title = stringResource(Res.string.auth_sign_up_duplicate_account_cta),
                        onClick = callbacks.onDismissDialog
                    ),
                    image = painterResource(resource = DsRes.drawable.img_error_default)
                )
            }
        }
    }
}
