package io.github.monolithic.invoicer.features.auth.presentation.screens.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_in_dont_have_account_prefix
import invoicer.features.auth.presentation.generated.resources.auth_sign_in_dont_have_account_suffix
import invoicer.features.auth.presentation.generated.resources.auth_sign_in_error
import invoicer.features.auth.presentation.generated.resources.auth_sign_in_forgot_password
import invoicer.features.auth.presentation.generated.resources.auth_sign_in_submit_button
import invoicer.features.auth.presentation.generated.resources.ic_danger_square
import invoicer.foundation.design_system.generated.resources.ic_chveron_left
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.components.LoginHeader
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.components.SignInForm
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkTextButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.InkSnackBarHost
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.InkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar.props.rememberInkSnackBarHostState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.navigation.extensions.pushToFront
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarBottomPadding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import invoicer.foundation.design_system.generated.resources.Res as DsRes

internal class LoginScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val viewModel = koinScreenModel<LoginScreenModel>()
        val state by viewModel.state.collectAsState()
        val scope = rememberCoroutineScope()
        val snackBarHost = rememberInkSnackBarHostState()
        val genericErrorMessage = stringResource(Res.string.auth_sign_in_error)
        val keyboard = LocalSoftwareKeyboardController.current
        val snackBarErrorIcon = painterResource(Res.drawable.ic_danger_square)

        StateContent(
            state = state,
            callBacks = rememberLoginCallbacks(
                onSubmit = {
                    keyboard?.hide()
                    viewModel.submitIdentityLogin()
                },
                onEmailChanged = viewModel::onEmailChanged,
                onPasswordChanged = viewModel::onPasswordChanged,
                onToggleCensorship = viewModel::toggleCensorship,
                onBack = { navigator?.pop() },
                onSignUpClick = {
                    navigator?.pushToFront(SignUpScreen())
                },
            ),
            snackbarHostState = snackBarHost
        )

        LaunchedEffect(viewModel.events) {
            viewModel.events.collectLatest {
                when (it) {
                    is LoginScreenEvents.Failure -> {
                        scope.launch {
                            snackBarHost.showSnackBar(
                                message = it.message,
                                leadingIcon = snackBarErrorIcon
                            )
                        }
                    }

                    LoginScreenEvents.GenericFailure -> scope.launch {
                        snackBarHost.showSnackBar(
                            message = genericErrorMessage,
                            leadingIcon = snackBarErrorIcon
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun StateContent(
        state: LoginScreenState,
        snackbarHostState: InkSnackBarHostState,
        callBacks: LoginScreenCallbacks
    ) {
        InkScaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                InkTopBar(
                    modifier = Modifier.statusBarsPadding(),
                    navigationIcon = painterResource(DsRes.drawable.ic_chveron_left),
                    onNavigationClick = callBacks.onBack,
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.auth_sign_in_submit_button),
                    enabled = state.buttonEnabled,
                    loading = state.isSignInLoading,
                    onClick = callBacks.onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = InkTheme.spacing.medium)
                        .systemBarBottomPadding()
                )
            },
            snackBarHost = {
                InkSnackBarHost(snackbarHostState)
            }
        ) {
            val scrollState = rememberScrollState()
            Column(
                modifier = Modifier
                    .padding(it)
                    .padding(horizontal = InkTheme.spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(scrollState)
            ) {
                LoginHeader()
                VerticalSpacer(height = SpacerSize.XLarge3)
                SignInForm(
                    state = state,
                    onPasswordChange = callBacks.onPasswordChanged,
                    onEmailChange = callBacks.onEmailChanged,
                    toggleCensorship = callBacks.toggleCensorship
                )
                InkTextButton(
                    text = stringResource(Res.string.auth_sign_in_forgot_password),
                    onClick = {},
                    modifier = Modifier.wrapContentWidth()
                )
                InkTextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = callBacks.onSignUpClick,
                    text = buildAnnotatedString {
                        withStyle(
                            style = InkTheme.typography.bodyXLarge.copy(
                                color = InkTheme.colorScheme.onBackground,
                                fontWeight = FontWeight.Normal
                            ).toSpanStyle(),
                        ) {
                            append(text = stringResource(Res.string.auth_sign_in_dont_have_account_prefix))
                        }
                        append(" ")
                        withStyle(
                            style = InkTheme.typography.bodyXLarge.copy(
                                color = InkTheme.colorScheme.primary,
                                fontWeight = FontWeight.SemiBold
                            ).toSpanStyle(),
                        ) {
                            append(text = stringResource(Res.string.auth_sign_in_dont_have_account_suffix))
                        }
                    }
                )
            }
        }
    }
}
