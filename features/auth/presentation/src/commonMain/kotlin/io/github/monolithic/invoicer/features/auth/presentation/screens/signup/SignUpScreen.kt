package io.github.monolithic.invoicer.features.auth.presentation.screens.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_description
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_duplicate_account_description
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_duplicate_account_title
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_error
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_have_account_prefix
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_have_account_suffix
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_submit_button
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_title
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components.PasswordStrengthCard
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components.SignUpForm
import io.github.monolithic.invoicer.features.auth.presentation.screens.signupfeedback.SignUpFeedbackScreen
import io.github.monolithic.invoicer.foundation.designSystem.components.DialogVariant
import io.github.monolithic.invoicer.foundation.designSystem.components.InvoicerDialog
import io.github.monolithic.invoicer.foundation.designSystem.components.ScreenTitle
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.BackButton
import io.github.monolithic.invoicer.foundation.designSystem.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalMaterial3Api::class)
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

        val snackBarState = remember {
            SnackbarHostState()
        }

        LaunchedEffect(viewModel.events) {
            viewModel.events.collectLatest {
                when (it) {
                    SignUpEvents.Success -> navigator?.push(SignUpFeedbackScreen())

                    is SignUpEvents.Failure -> {
                        scope.launch {
                            snackBarState.showSnackbar(
                                message = it.message
                            )
                        }
                    }

                    SignUpEvents.GenericFailure -> scope.launch {
                        snackBarState.showSnackbar(
                            message = genericErrorMessage
                        )
                    }

                    SignUpEvents.DuplicateAccount -> showDuplicateAccountDialog = true
                }
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
        snackBarState: SnackbarHostState,
        callbacks: SignUpCallbacks,
        showDuplicateAccountDialog: Boolean,
    ) {
        val scrollState = rememberScrollState()
        Scaffold(
            modifier = Modifier.imePadding(),
            topBar = {
                TopAppBar(
                    title = {},
                    navigationIcon = {
                        BackButton(onBackClick = callbacks.onBackClick)
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(snackBarState)
            },
            bottomBar = {
                PrimaryButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(Spacing.medium),
                    label = stringResource(Res.string.auth_sign_up_submit_button),
                    onClick = callbacks.onSubmitClick,
                    isEnabled = state.buttonEnabled,
                    isLoading = state.requestLoading
                )

            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(Spacing.medium)
                    .fillMaxSize()
                    .verticalScroll(scrollState),
            ) {
                VerticalSpacer(height = SpacerSize.XLarge3)
                ScreenTitle(
                    title = stringResource(Res.string.auth_sign_up_title),
                    subTitle = stringResource(Res.string.auth_sign_up_description)
                )
                VerticalSpacer(height = SpacerSize.XLarge3)
                SignUpForm(
                    modifier = Modifier.fillMaxWidth(),
                    state = state,
                    onPasswordChange = callbacks.onPasswordChange,
                    onEmailChange = callbacks.onEmailChange,
                    toggleCensorship = callbacks.toggleCensorship
                )
                VerticalSpacer(height = SpacerSize.XLarge)
                PasswordStrengthCard(
                    passwordStrength = state.passwordStrength,
                    modifier = Modifier.fillMaxWidth()
                )
                VerticalSpacer(height = SpacerSize.XLarge)
                TextButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = callbacks.onSignInClick
                ) {
                    Text(
                        text = buildAnnotatedString {
                            withStyle(
                                style = MaterialTheme.typography.bodyMedium
                                    .copy(color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    .toSpanStyle()
                            ) {
                                append(text = stringResource(Res.string.auth_sign_up_have_account_prefix))
                            }
                            append(" ")
                            withStyle(
                                style = MaterialTheme.typography.bodyMedium
                                    .copy(color = MaterialTheme.colorScheme.primary)
                                    .toSpanStyle()
                            ) {
                                append(text = stringResource(Res.string.auth_sign_up_have_account_suffix))
                            }
                        }
                    )
                }
            }

            if (showDuplicateAccountDialog) {
                InvoicerDialog(
                    onDismiss = callbacks.onDismissDialog,
                    variant = DialogVariant.Error,
                    title = stringResource(Res.string.auth_sign_up_duplicate_account_title),
                    description = stringResource(
                        Res.string.auth_sign_up_duplicate_account_description,
                        state.email
                    )
                )
            }
        }
    }
}
