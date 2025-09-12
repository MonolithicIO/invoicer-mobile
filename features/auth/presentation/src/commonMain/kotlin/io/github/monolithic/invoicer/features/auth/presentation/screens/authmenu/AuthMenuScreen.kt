package io.github.monolithic.invoicer.features.auth.presentation.screens.authmenu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import invoicer.features.auth.presentation.generated.resources.Res
import invoicer.features.auth.presentation.generated.resources.auth_menu_apple_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_description
import invoicer.features.auth.presentation.generated.resources.auth_menu_facebook_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_google_button
import invoicer.features.auth.presentation.generated.resources.auth_menu_sign_in
import invoicer.features.auth.presentation.generated.resources.auth_menu_sign_up
import invoicer.features.auth.presentation.generated.resources.auth_menu_title
import invoicer.features.auth.presentation.generated.resources.auth_menu_twitter_button
import invoicer.features.auth.presentation.generated.resources.ic_apple_dark
import invoicer.features.auth.presentation.generated.resources.ic_apple_light
import invoicer.features.auth.presentation.generated.resources.ic_facebook
import invoicer.features.auth.presentation.generated.resources.ic_google
import invoicer.features.auth.presentation.generated.resources.ic_twitter_dark
import invoicer.features.auth.presentation.generated.resources.ic_twitter_light
import invoicer.features.auth.presentation.generated.resources.logo
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSocialButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

internal class AuthMenuScreen : Screen {
    @Composable
    override fun Content() {
        AuthMenuScreenContent()
    }

    @Composable
    internal fun AuthMenuScreenContent() {
        InkScaffold(
            modifier = Modifier.systemBarsPadding()
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(InkTheme.spacing.medium),
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(Res.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .size(80.dp)
                )
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
                        onClick = {}
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
                    InkSocialButton(
                        modifier = Modifier.fillMaxWidth(),
                        text = stringResource(Res.string.auth_menu_twitter_button),
                        contentDescription = null,
                        iconPainter = twitterIcon(),
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
        }
    }

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

@Composable
private fun twitterIcon(): Painter {
    val isDarkTheme = InkTheme.isDark

    return if (isDarkTheme) {
        painterResource(Res.drawable.ic_twitter_dark)
    } else {
        painterResource(Res.drawable.ic_twitter_light)
    }
}