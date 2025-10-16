package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.success

import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.annotation.InternalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.internal.BackHandler
import io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.home.AuthorizationHomeScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar

internal class AuthorizationSuccessScreen : Screen {

    @OptIn(InternalVoyagerApi::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        BackHandler(true) { }

        InkScaffold(
            topBar = {
                InkTopBar(
                    onNavigationClick = {
                        navigator?.popUntil {
                            it is AuthorizationHomeScreen
                        }
                    },
                )
            }
        ) {
            InkText(modifier = Modifier.padding(it), text = "code authorized")
        }
    }
}
