package io.github.monolithic.invoicer.features.auth.presentation.screens.signupfeedback

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_feedback_cta
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_feedback_message
import invoicer.multiplatform.features.auth.generated.resources.auth_sign_up_feedback_title
import invoicer.multiplatform.features.auth.generated.resources.img_sign_up_success
import io.github.monolithic.invoicer.features.auth.presentation.screens.login.LoginScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
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

    @Composable
    fun StateContent(
        onBack: () -> Unit,
    ) {
        InkScaffold(
            bottomBar = {
                InkPrimaryButton(
                    text = stringResource(Res.string.auth_sign_up_feedback_cta),
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center

            ) {
                Image(
                    painter = painterResource(Res.drawable.img_sign_up_success),
                    contentDescription = null
                )
                VerticalSpacer(height = SpacerSize.XLarge3)
                InkText(
                    text = stringResource(Res.string.auth_sign_up_feedback_title),
                    style = InkTextStyle.Heading3,
                    weight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
                VerticalSpacer(height = SpacerSize.Small)
                InkText(
                    text = stringResource(Res.string.auth_sign_up_feedback_message),
                    style = InkTextStyle.BodyXlarge,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
