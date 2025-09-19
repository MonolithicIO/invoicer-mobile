package io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_email_error
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_email_label
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_email_placeholder
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_password_label
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_weak_password_digit
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_weak_password_length
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_weak_password_lowercase
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_weak_password_special
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_weak_password_uppercase
import invoicer.features.auth.presentation.generated.resources.ic_email
import invoicer.features.auth.presentation.generated.resources.ic_lock
import invoicer.features.auth.presentation.generated.resources.ic_visibility_off
import invoicer.features.auth.presentation.generated.resources.ic_visibility_on
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenState
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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

        VerticalSpacer(SpacerSize.XSmall)

        SignUpPasswordField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(passwordFocus),
            value = state.password,
            onChange = onPasswordChange,
            isCensored = state.censored,
            toggleCensorship = toggleCensorship,
            onImeAction = { keyboard?.hide() },
            enabled = state.requestLoading.not(),
            passwordIssue = state.currentPasswordIssue
        )
    }
}

@Composable
private fun SignUpEmailField(
    value: String,
    enabled: Boolean,
    isEmailValid: Boolean,
    onChange: (String) -> Unit,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val supportText = if (isEmailValid) {
        null
    } else {
        stringResource(Res.string.auth_sign_up_email_error)
    }

    val trailingIcon = remember(isEmailValid) {
        if (isEmailValid) null
        else Icons.Outlined.ErrorOutline
    }

    InkOutlinedInput(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        label = stringResource(Res.string.auth_sign_up_email_label),
        placeholder = stringResource(Res.string.auth_sign_up_email_placeholder),
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
        isError = isEmailValid.not(),
        supportText = supportText,
        trailingContent = if (trailingIcon != null) {
            {
                Icon(
                    painter = rememberVectorPainter(
                        image = trailingIcon
                    ),
                    contentDescription = null
                )
            }
        } else null,
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_email),
                contentDescription = null,
                tint = InkTheme.colorScheme.onSurfaceVariant
            )
        },
        isEnabled = enabled
    )
}

@Composable
private fun SignUpPasswordField(
    passwordIssue: PasswordIssue?,
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

    val supportText = when (passwordIssue) {
        PasswordIssue.LENGTH -> stringResource(Res.string.auth_sign_up_weak_password_length)
        PasswordIssue.UPPERCASE -> stringResource(Res.string.auth_sign_up_weak_password_uppercase)
        PasswordIssue.LOWERCASE -> stringResource(Res.string.auth_sign_up_weak_password_lowercase)
        PasswordIssue.DIGIT -> stringResource(Res.string.auth_sign_up_weak_password_digit)
        PasswordIssue.SPECIAL_CHARACTER -> stringResource(Res.string.auth_sign_up_weak_password_special)
        null -> null
    }

    InkOutlinedInput(
        value = value,
        onValueChange = onChange,
        modifier = modifier,
        trailingContent = {
            InkIconButton(
                onClick = toggleCensorship,
                icon = painterResource(trailingIcon)
            )
        },
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_lock),
                contentDescription = null,
                tint = InkTheme.colorScheme.onSurfaceVariant
            )
        },
        visualTransformation = transformation,
        label = stringResource(Res.string.auth_sign_up_password_label),
        placeholder = stringResource(Res.string.auth_sign_up_password_label),
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
        supportText = supportText,
        isError = supportText != null
    )
}
