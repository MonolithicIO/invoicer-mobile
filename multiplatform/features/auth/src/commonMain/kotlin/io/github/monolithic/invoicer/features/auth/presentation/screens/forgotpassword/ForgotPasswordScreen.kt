package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import cafe.adriel.voyager.core.screen.Screen
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
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.Title
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class ForgotPasswordScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        StateContent(
            state = ForgotPasswordState(),
            actions = remember {
                Actions(
                    onBack = { navigator?.pop() },
                    onChangeEmail = {},
                    submit = { }
                )
            }
        )
    }

    @Composable
    fun StateContent(
        actions: Actions,
        state: ForgotPasswordState
    ) {
        InkScaffold(
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
                    enabled = state.buttonEnabled
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(InkTheme.spacing.medium)
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
                    maxLines = 1,
                    placeholder = stringResource(Res.string.forgot_password_placeholder)
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