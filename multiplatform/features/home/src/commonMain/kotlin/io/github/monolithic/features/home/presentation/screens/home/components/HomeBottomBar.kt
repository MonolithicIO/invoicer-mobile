package io.github.monolithic.features.home.presentation.screens.home.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.tab.Tab
import io.github.monolithic.features.home.presentation.screens.home.tabs.settings.SettingsTab
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.WelcomeTab

@Composable
internal fun HomeBottomBar(
    modifier: Modifier = Modifier,
    selectedTab: Tab,
    onSelectTab: (Tab) -> Unit
) {
    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            selected = selectedTab is WelcomeTab,
            onClick = { onSelectTab(WelcomeTab) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Home,
                    contentDescription = null
                )
            }
        )

        NavigationBarItem(
            selected = selectedTab is SettingsTab,
            onClick = { onSelectTab(SettingsTab) },
            icon = {
                Icon(
                    imageVector = Icons.Outlined.Settings,
                    contentDescription = null
                )
            }
        )
    }
}
