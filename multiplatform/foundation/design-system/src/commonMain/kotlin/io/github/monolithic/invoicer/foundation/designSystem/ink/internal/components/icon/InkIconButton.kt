package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.BasicInkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.props.InkIconButtonColors

@Composable
fun InkIconButton(
    onClick: () -> Unit,
    icon: Painter,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    colors: InkIconButtonColors = InkIconButtonDefaults.colors,
) {
    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource,
        colors = colors
    ) {
        InkIcon(
            painter = icon,
            contentDescription = null,
            tint = if (enabled) colors.iconColor else colors.disabledIconColor
        )
    }
}

@Composable
fun InkIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    icon: ImageBitmap,
    colors: InkIconButtonColors = InkIconButtonDefaults.colors,
) {
    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        InkIcon(
            bitmap = icon,
            contentDescription = null,
            tint = if (enabled) colors.iconColor else colors.disabledIconColor
        )
    }
}

@Composable
fun InkIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    icon: ImageVector,
    colors: InkIconButtonColors = InkIconButtonDefaults.colors,
) {
    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        InkIcon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) colors.iconColor else colors.disabledIconColor
        )
    }
}
