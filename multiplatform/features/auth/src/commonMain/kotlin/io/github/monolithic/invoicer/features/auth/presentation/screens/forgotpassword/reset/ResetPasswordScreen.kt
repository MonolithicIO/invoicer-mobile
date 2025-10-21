package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.reset

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_confirm_password_label
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_description
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_new_password_label
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_reset_title
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_rest_cta
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_lock
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_visibility_off
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_visibility_on
import io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.components.CloseForgotPasswordDialog
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.features.auth.presentation.utils.textResource
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ResetPasswordScreen(
    private val resetToken: String
) : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ResetPasswordScreenModel>()
        val state by screenModel.state.collectAsState()
        val navigator = LocalNavigator.current
        var showExitDialog by remember { mutableStateOf(false) }

        BackHandler(enabled = true) { showExitDialog = true }

        StateContent(
            state = state,
            showExitDialog = showExitDialog,
            actions = remember {
                Actions(
                    onUpdatePassword = screenModel::updatePassword,
                    onUpdateConfirmPassword = screenModel::updateConfirmPassword,
                    onSubmit = { screenModel.submit(resetToken) },
                    dismissDialog = { shouldExitFlow ->
                        showExitDialog = false
                        if (shouldExitFlow) {
                            navigator?.popUntil { it is LoginScreen }
                        }
                    },
                    onBack = { showExitDialog = true },
                    onTogglePasswordVisibility = screenModel::togglePasswordCensorship,
                    onToggleConfirmPasswordVisibility = screenModel::toggleConfirmPasswordCensorship
                )
            }
        )
    }

    @Composable
    fun StateContent(
        state: ResetPasswordState,
        actions: Actions,
        showExitDialog: Boolean,
    ) {
        val showPasswordError by remember {
            derivedStateOf {
                state.passwordError != null
            }
        }

        val (passwordRef, confirmRef) = FocusRequester.createRefs()
        val keyboard = LocalSoftwareKeyboardController.current

        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = actions.onBack,
                    modifier = Modifier.statusBarsPadding()
                )
            },
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.forgot_password_rest_cta),
                    onClick = actions.onSubmit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding(),
                    loading = state.isLoading
                )
            },
            modifier = Modifier.imePadding()
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                Title(
                    title = stringResource(Res.string.forgot_password_reset_title),
                    subtitle = stringResource(Res.string.forgot_password_reset_description)
                )
                VerticalSpacer(SpacerSize.Medium)

                InkOutlinedInput(
                    value = state.password,
                    onValueChange = actions.onUpdatePassword,
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(passwordRef),
                    isError = showPasswordError,
                    readOnly = state.isLoading.not(),
                    supportText = state.passwordError.textResource(),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_lock),
                            contentDescription = null,
                            tint = if (showPasswordError) InkTheme.colorScheme.error
                            else InkTheme.colorScheme.onSurface
                        )
                    },
                    trailingContent = {
                        InkIconButton(
                            onClick = actions.onTogglePasswordVisibility,
                            icon = painterResource(
                                if (state.isPasswordCensored) DsResources.drawable.ic_visibility_off
                                else DsResources.drawable.ic_visibility_on
                            )
                        )
                    },
                    label = stringResource(Res.string.forgot_password_reset_new_password_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Next,
                        autoCorrectEnabled = false,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { confirmRef.requestFocus() }
                    )
                )

                InkOutlinedInput(
                    value = state.password,
                    onValueChange = actions.onUpdatePassword,
                    modifier = Modifier.fillMaxWidth().focusRequester(confirmRef),
                    readOnly = state.isLoading.not(),
                    leadingContent = {
                        InkIcon(
                            painter = painterResource(DsResources.drawable.ic_lock),
                            contentDescription = null,
                            tint = if (showPasswordError) InkTheme.colorScheme.error
                            else InkTheme.colorScheme.onSurface
                        )
                    },
                    trailingContent = {
                        InkIconButton(
                            onClick = actions.onToggleConfirmPasswordVisibility,
                            icon = painterResource(
                                if (state.isConfirmPasswordCensored) DsResources.drawable.ic_visibility_off
                                else DsResources.drawable.ic_visibility_on
                            )
                        )
                    },
                    label = stringResource(Res.string.forgot_password_reset_confirm_password_label),
                    keyboardOptions = KeyboardOptions(
                        imeAction = ImeAction.Done,
                        autoCorrectEnabled = false,
                    ),
                    keyboardActions = KeyboardActions(
                        onDone = { keyboard?.hide() }
                    ),
                    isError = state.passwordsMatch.not()
                )
            }
        }

        if (showExitDialog) {
            CloseForgotPasswordDialog(
                onDismissDialog = { actions.dismissDialog(false) },
                onCloseFlow = { actions.dismissDialog(true) }
            )
        }
    }

    data class Actions(
        val onUpdatePassword: (String) -> Unit,
        val onUpdateConfirmPassword: (String) -> Unit,
        val onSubmit: () -> Unit,
        val dismissDialog: (exitFlow: Boolean) -> Unit,
        val onBack: () -> Unit,
        val onTogglePasswordVisibility: () -> Unit,
        val onToggleConfirmPasswordVisibility: () -> Unit
    )
}