package io.github.monolithic.invoicer.features.auth.presentation.screens.login.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_in_email_label
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_in_password_label
import invoicer.multiplatform.features.auth.generated.resources.ic_email
import invoicer.multiplatform.features.auth.generated.resources.ic_lock
import invoicer.multiplatform.features.auth.generated.resources.ic_visibility_off
import invoicer.multiplatform.features.auth.generated.resources.ic_visibility_on
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreenState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SignInForm(
    modifier: Modifier = Modifier,
    state: LoginScreenState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    toggleCensorship: () -> Unit,
) {
    val (emailFocus, passwordFocus) = FocusRequester.createRefs()
    val focus = LocalFocusManager.current

    Column(
        modifier = modifier,
    ) {
        LoginEmailField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(emailFocus),
            value = state.email,
            onChange = onEmailChange,
            onImeAction = { passwordFocus.requestFocus() },
            enabled = state.isSignInLoading.not()
        )
        VerticalSpacer(SpacerSize.XSmall)
        LoginPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocus),
            value = state.password,
            onChange = onPasswordChange,
            onImeAction = {
                focus.clearFocus()
            },

            enabled = state.isSignInLoading.not(),
            isCensored = state.censored,
            toggleCensorship = toggleCensorship
        )
    }
}

@Composable
internal fun LoginPasswordField(
    value: String,
    isCensored: Boolean,
    enabled: Boolean,
    onChange: (String) -> Unit,
    toggleCensorship: () -> Unit,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val trailingIcon = remember(isCensored) {
        if (isCensored) {
            Res.drawable.ic_visibility_on
        } else {
            Res.drawable.ic_visibility_off
        }
    }

    val transformation = remember(isCensored) {

        if (isCensored) {
            PasswordVisualTransformation(mask = '●')
        } else {
            VisualTransformation.None
        }
    }

    InkOutlinedInput(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_lock),
                contentDescription = null,
                tint = InkTheme.colorScheme.onSurfaceVariant
            )
        },
        trailingContent = {
            InkIconButton(
                onClick = toggleCensorship,
                icon = painterResource(resource = trailingIcon)
            )
        },
        visualTransformation = transformation,
        label = stringResource(Res.string.auth_sign_in_password_label),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            autoCorrectEnabled = false,
            keyboardType = KeyboardType.Password,
        ),
        keyboardActions = KeyboardActions(
            onNext = { onImeAction() }
        ),
        isEnabled = enabled,
        placeholder = stringResource(Res.string.auth_sign_in_password_label)
    )
}

@Composable
internal fun LoginEmailField(
    value: String,
    enabled: Boolean,
    onChange: (String) -> Unit,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    InkOutlinedInput(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        label = stringResource(Res.string.auth_sign_in_email_label),
        placeholder = stringResource(Res.string.auth_sign_in_email_label),
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_email),
                contentDescription = null,
                tint = InkTheme.colorScheme.onSurfaceVariant
            )
        },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Next,
            capitalization = KeyboardCapitalization.None,
            keyboardType = KeyboardType.Email,
            autoCorrectEnabled = false,
        ),
        keyboardActions = KeyboardActions(
            onNext = { onImeAction() }
        ),
        isEnabled = enabled,
    )
}
