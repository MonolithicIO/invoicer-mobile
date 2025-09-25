package io.github.monolithic.features.home.presentation.screens.home.tabs.account

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText

internal object AccountTab : Tab {
    override val options: TabOptions
        @Composable
        get() = TabOptions(
            index = 2u,
            title = "",
            icon = null
        )

    @Composable
    override fun Content() {
        InkText("account")
    }
}
