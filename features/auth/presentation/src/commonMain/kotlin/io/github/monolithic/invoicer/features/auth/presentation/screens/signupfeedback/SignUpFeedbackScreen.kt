package io.github.monolithic.invoicer.features.auth.presentation.screens.signupfeedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_feedback_cta
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_feedback_message
import invoicer.features.auth.presentation.generated.resources.auth_sign_up_feedback_title
import invoicer.features.auth.presentation.generated.resources.img_sign_up_success
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarBottomPadding
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class SignUpFeedbackScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        BackHandler(true) {
            // Disable system back
        }

        StateContent(
            onBack = { navigator?.replaceAll(LoginScreen()) }
        )
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun StateContent(
        onBack: () -> Unit,
    ) {
        InkScaffold(
            bottomBar = {
                PrimaryButton(
                    label = stringResource(Res.string.auth_sign_up_feedback_cta),
                    onClick = onBack,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = InkTheme.spacing.medium)
                        .systemBarBottomPadding()
                )
            }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .systemBarsPadding()
                    .padding(it)
                    .padding(Spacing.medium),
                horizontalAlignment = Alignment.CenterHorizontally

            ) {
                Image(
                    painter = painterResource(Res.drawable.img_sign_up_success),
                    contentDescription = null
                )
                InkText(
                    text = stringResource(Res.string.auth_sign_up_feedback_title),
                    style = InkTextStyle.Heading3,
                    weight = FontWeight.Bold
                )
                InkText(
                    text = stringResource(Res.string.auth_sign_up_feedback_message),
                    style = InkTextStyle.Heading3,
                    weight = FontWeight.Bold
                )
            }
        }
    }
}
