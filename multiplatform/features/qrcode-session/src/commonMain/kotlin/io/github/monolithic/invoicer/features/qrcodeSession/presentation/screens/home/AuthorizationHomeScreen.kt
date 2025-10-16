package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import invoicer.multiplatform.features.qrcode_session.generated.resources.Res
import invoicer.multiplatform.features.qrcode_session.generated.resources.qrcode_session_home_authorize_cta
import invoicer.multiplatform.features.qrcode_session.generated.resources.qrcode_session_home_title
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.scan.AuthorizationScanScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

internal class AuthorizationHomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        StateContent(
            onOpenCodeScanner = { navigator?.push(AuthorizationScanScreen()) },
            onBackPressed = { navigator?.pop() }
        )
    }

    @Composable
    fun StateContent(
        onOpenCodeScanner: () -> Unit,
        onBackPressed: () -> Unit,
    ) {
        InkScaffold(
            topBar = {
                InkTopBar(
                    title = stringResource(Res.string.qrcode_session_home_title),
                    onNavigationClick = onBackPressed,
                    modifier = Modifier.statusBarsPadding()
                )
            }
        ) { scaffoldPadding ->
            Column(
                modifier = Modifier
                    .padding(scaffoldPadding)
                    .padding(InkTheme.spacing.medium)
                    .navigationBarsPadding()
            ) {
                InkPrimaryButton(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onOpenCodeScanner,
                    text = stringResource(Res.string.qrcode_session_home_authorize_cta)
                )
            }
        }
    }
}
