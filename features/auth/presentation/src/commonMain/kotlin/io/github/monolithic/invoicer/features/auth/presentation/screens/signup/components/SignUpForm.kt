package io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer

@Composable
internal fun SignUpForm(
    modifier: Modifier = Modifier,
    state: SignUpScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    toggleCensorship: () -> Unit,
) {
    val (emailFocus, passwordFocus) = FocusRequester.createRefs()
    val keyboard = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
    ) {
        SignUpEmailField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailFocus),
            value = state.email,
            onChange = onEmailChange,
            onImeAction = { passwordFocus.requestFocus() },
            isEmailValid = state.emailValid,
            enabled = state.requestLoading.not()
        )

        VerticalSpacer(SpacerSize.Medium)

        SignUpPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocus),
            value = state.password,
            onChange = onPasswordChange,
            isCensored = state.censored,
            toggleCensorship = toggleCensorship,
            onImeAction = { keyboard?.hide() },
            enabled = state.requestLoading.not()
        )
    }
}
