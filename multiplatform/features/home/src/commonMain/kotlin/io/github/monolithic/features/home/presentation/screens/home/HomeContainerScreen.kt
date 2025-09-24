package io.github.monolithic.features.home.presentation.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import io.github.monolithic.features.home.presentation.screens.home.components.HomeBottomBar
import io.github.monolithic.features.home.presentation.screens.home.components.HomeTopBar
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTab
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.scaffold.InkScaffold
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarBottomPadding
import io.github.monolithic.invoicer.foundation.utils.modifier.systemBarTopPadding

internal class HomeContainerScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        StateContent(
            state = state
        )

    }

    @Composable
    fun StateContent(
        state: HomeScreenState,
    ) {
        TabNavigator(WelcomeTab) { navigator ->
            InkScaffold(
                topBar = {
                    HomeTopBar(
                        companyName = state.companyName,
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                    )
                },
                bottomBar = {
                    HomeBottomBar(
                        modifier = Modifier.systemBarBottomPadding(),
                        selectedTab = navigator.current,
                        onSelectTab = { newTab ->
                            navigator.current = newTab
                        }
                    )
                }
            ) {
                Box(
                    modifier = Modifier.Companion
                        .fillMaxSize()
                        .padding(it)
                ) {
                    CurrentTab()
                }
            }
        }
    }
}