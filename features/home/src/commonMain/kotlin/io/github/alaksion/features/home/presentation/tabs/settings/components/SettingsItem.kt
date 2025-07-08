package io.github.alaksion.features.home.presentation.tabs.settings.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import io.github.alaksion.invoicer.foundation.designSystem.components.ListItem

@Composable
internal fun SettingsItem(
    content: String,
    icon: ImageVector,
    iconTint: Color = LocalContentColor.current,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    ListItem(
        modifier = modifier.clickable(onClick = onClick),
        leadingContent = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        content = {
            Text(content)
        },
        trailingContent = {
            IconButton(onClick) {
                Icon(
                    painter = rememberVectorPainter(Icons.Default.ChevronRight),
                    contentDescription = null,
                    tint = iconTint
                )
            }
        }

    )
}
