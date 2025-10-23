package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.feedback

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_feedback_cta
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_success_description
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_success_title
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.SuccessState
import org.jetbrains.compose.resources.stringResource

internal class ResetPasswordFeedbackScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        BackHandler(true) {
            // no-op
        }

        InkScaffold(
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.auth_sign_up_feedback_cta),
                    onClick = { navigator?.popUntil { it is LoginScreen } },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(InkTheme.spacing.medium)
                        .navigationBarsPadding()
                )
            }
        ) { scaffoldPadding ->
            SuccessState(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(InkTheme.spacing.medium)
                    .padding(scaffoldPadding),
                title = stringResource(Res.string.forgot_password_success_title),
                description = stringResource(Res.string.forgot_password_success_description),
            )
        }
    }
}
