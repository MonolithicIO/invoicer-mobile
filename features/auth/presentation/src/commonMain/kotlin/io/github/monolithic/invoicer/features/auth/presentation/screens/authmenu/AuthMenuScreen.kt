package io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_menu_apple_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_description
import invoicer.features.auth.presentation.generated.resources.auth_menu_facebook_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_google_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_sign_in
import invoicer.features.auth.presentation.generated.resources.auth_menu_sign_up
import invoicer.features.auth.presentation.generated.resources.auth_menu_title
import invoicer.features.auth.presentation.generated.resources.ic_apple_dark
import invoicer.features.auth.presentation.generated.resources.ic_apple_light
import invoicer.features.auth.presentation.generated.resources.ic_facebook
import invoicer.features.auth.presentation.generated.resources.ic_google
import invoicer.features.auth.presentation.generated.resources.ic_logo
import invoicer.features.auth.presentation.generated.resources.ic_twitter_dark
import invoicer.features.auth.presentation.generated.resources.ic_twitter_light
import io.github.monolithic.invoicer.foundation.auth.presentation.rememberGoogleLauncher
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSocialButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.ui.FlowCollectEffect
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class AuthMenuScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<AuthMenuScreenModel>()
        val scope = rememberCoroutineScope()
        val state by screenModel.state.collectAsState()

        val googleLauncher = rememberGoogleLauncher(
            onSuccess = screenModel::handleGoogleSuccess,
            onFailure = screenModel::handleGoogleError,
            onCancel = screenModel::cancelSocialLogin
        )

        FlowCollectEffect(
            flow = screenModel.events,
            screenModel
        ) {
            when (it) {
                is AuthMenuUiEvents.SocialLoginError -> {
                    print("Social login error: ${it.message}")
                }

                AuthMenuUiEvents.LaunchGoogleSignIn -> scope.launch {
                    googleLauncher.launch()
                }
            }
        }

        AuthMenuScreenContent(
            state = state,
            actions = Actions(
                launchGoogleSignIn = screenModel::launchGoogleLogin
            )
        )
    }

    @Composable
    internal fun AuthMenuScreenContent(
        state: AuthMenuState,
        actions: Actions
    ) {
        InkScaffold {
            when (state.mode) {
                AuthMenuMode.Idle -> Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(InkTheme.spacing.medium),
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_logo),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .size(80.dp)
                    )
                    VerticalSpacer(SpacerSize.XLarge3)
                    InkText(
                        text = stringResource(Res.string.auth_menu_title),
                        style = InkTextStyle.Heading3,
                        weight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                    VerticalSpacer(SpacerSize.Small)
                    InkText(
                        text = stringResource(Res.string.auth_menu_description),
                        style = InkTextStyle.BodyXlarge,
                        weight = FontWeight.Normal,
                        color = InkTheme.colorScheme.onBackgroundVariant,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    VerticalSpacer(SpacerSize.XLarge3)
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
                    ) {
                        InkSocialButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.auth_menu_google_button),
                            contentDescription = null,
                            iconPainter = painterResource(Res.drawable.ic_google),
                            onClick = actions.launchGoogleSignIn,
                        )
                        InkSocialButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.auth_menu_apple_button),
                            contentDescription = null,
                            iconPainter = appleIcon(),
                            onClick = {}
                        )
                        InkSocialButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.auth_menu_facebook_button),
                            contentDescription = null,
                            iconPainter = painterResource(Res.drawable.ic_facebook),
                            onClick = {}
                        )
                        InkPrimaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.auth_menu_sign_up),
                            onClick = {}
                        )
                        InkSecondaryButton(
                            modifier = Modifier.fillMaxWidth(),
                            text = stringResource(Res.string.auth_menu_sign_in),
                            onClick = {}
                        )
                    }
                }

                AuthMenuMode.Loading -> Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(InkTheme.spacing.medium),
                    contentAlignment = Alignment.Center
                ) {
                    InkCircularIndicator(
                        color = InkTheme.colorScheme.primary
                    )
                }
            }
        }
    }

    data class Actions(
        val launchGoogleSignIn: () -> Unit,
    )

}

@Composable
private fun appleIcon(): Painter {
    val isDarkTheme = InkTheme.isDark

    return if (isDarkTheme) {
        painterResource(Res.drawable.ic_apple_dark)
    } else {
        painterResource(Res.drawable.ic_apple_light)
    }
}
