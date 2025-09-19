package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronLeft
import androidx.compose.material.icons.outlined.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.topbar.InkTopBar
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InkTheme

@Composable
@Preview
private fun TopBar() {
    InkTheme {
        Column(
            modifier = Modifier
                .background(InkTheme.colorScheme.background)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InkTopBar(
                navigationIcon = rememberVectorPainter(Icons.Outlined.ChevronLeft),
                onNavigationClick = {}
            )

            InkTopBar(
                navigationIcon = rememberVectorPainter(Icons.Outlined.ChevronLeft),
                onNavigationClick = {},
                title = "With title"
            )

            InkTopBar(
                navigationIcon = rememberVectorPainter(Icons.Outlined.ChevronLeft),
                onNavigationClick = {},
                title = "With title",
                actions = {
                    InkIcon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = null
                    )
                }
            )
        }
    }
}
