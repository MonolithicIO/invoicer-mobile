package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkCircleButton(
    onClick: () -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    containerColor: Color = InkCircleButtonDefaults.containerColor,
    iconColor: Color = InkCircleButtonDefaults.iconColor,
    interactionSource: MutableInteractionSource? = null,
) {
    Surface(
        onClick = onClick,
        modifier = modifier.semantics { role = Role.Button },
        shape = CircleShape,
        contentColor = iconColor,
        color = containerColor,
        interactionSource = interactionSource
    ) {
        Box(
            modifier = Modifier.defaultMinSize(
                minHeight = InkCircleButtonDefaults.ContainerSize,
                minWidth = InkCircleButtonDefaults.ContainerSize
            ),
            contentAlignment = Alignment.Center
        ) {
            InkIcon(
                painter = icon,
                contentDescription = null,
                tint = iconColor,
            )
        }
    }
}

object InkCircleButtonDefaults {

    internal val ContainerSize = 56.0.dp
    val containerColor
        @Composable
        get() = InkTheme.colorScheme.primary

    val iconColor
        @Composable
        get() = InkTheme.colorScheme.onPrimary

}