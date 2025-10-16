package io.github.monolithic.invoicer.features.auth.presentation.screens.signup.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component1
import androidx.compose.ui.focus.FocusRequester.Companion.FocusRequesterFactory.component2
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_duplicate_account_title
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_email_error
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_email_label
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_email_placeholder
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_password_label
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_digit
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_length
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_lowercase
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_special
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_weak_password_uppercase
import invoicer.multiplatform.features.auth.generated.resources.ic_danger_square
import invoicer.multiplatform.features.auth.generated.resources.ic_email
import invoicer.multiplatform.features.auth.generated.resources.ic_lock
import invoicer.multiplatform.features.auth.generated.resources.ic_visibility_off
import invoicer.multiplatform.features.auth.generated.resources.ic_visibility_on
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpEmailIssue
import io.github.monolithic.invoicer.features.auth.presentation.screens.signup.SignUpScreenState
import io.github.monolithic.invoicer.features.auth.presentation.utils.PasswordIssue
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
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
            enabled = state.requestLoading.not(),
            emailIssue = state.currentEmailIssue
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
    emailIssue: SignUpEmailIssue?,
    onChange: (String) -> Unit,
    onImeAction: () -> Unit,
    modifier: Modifier = Modifier
) {
    val duplicateEmailText = stringResource(Res.string.auth_sign_up_duplicate_account_title)
    val invalidEmailText = stringResource(Res.string.auth_sign_up_email_error)

    val supportText by derivedStateOf {
        when (emailIssue) {
            SignUpEmailIssue.DuplicateAccount -> duplicateEmailText
            SignUpEmailIssue.InvalidFormat -> invalidEmailText
            null -> null
        }
    }

    val trailingIcon = if (emailIssue == null) null
    else painterResource(resource = Res.drawable.ic_danger_square)

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
        isError = emailIssue != null,
        supportText = supportText,
        trailingContent = if (trailingIcon != null) {
            {
                InkIcon(
                    painter = trailingIcon,
                    contentDescription = null,
                    tint = InkTheme.colorScheme.error
                )
            }
        } else null,
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_email),
                contentDescription = null,
                tint = if (emailIssue == null) InkTheme.colorScheme.onSurfaceVariant
                else InkTheme.colorScheme.error
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
    val colors = InkTheme.colorScheme

    val trailingIcon = remember(isCensored) {
        if (isCensored) {
            Res.drawable.ic_visibility_on
        } else {
            Res.drawable.ic_visibility_off
        }
    }

    val iconColor by derivedStateOf {
        if (passwordIssue == null) colors.onSurfaceVariant
        else colors.error
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
                icon = painterResource(trailingIcon),
                colors = InkIconButtonDefaults.colors.copy(
                    iconColor = iconColor
                )
            )
        },
        leadingContent = {
            InkIcon(
                painter = painterResource(Res.drawable.ic_lock),
                contentDescription = null,
                tint = iconColor
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
