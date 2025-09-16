package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.BasicInkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.BasicInkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    icon: Painter,
    iconTint: Color = InkTheme.colorScheme.onSurfaceVariant,
) {
    val colors = BasicInkIconButtonDefaults.colors

    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        InkIcon(
            painter = icon,
            contentDescription = null,
            tint = if (enabled) iconTint else colors.disabledContentColor
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
    iconTint: Color = InkTheme.colorScheme.onSurfaceVariant,
) {
    val colors = BasicInkIconButtonDefaults.colors

    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        InkIcon(
            bitmap = icon,
            contentDescription = null,
            tint = if (enabled) iconTint else colors.disabledContentColor
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
    iconTint: Color = InkTheme.colorScheme.onSurfaceVariant,
) {
    val colors = BasicInkIconButtonDefaults.colors

    BasicInkIconButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        InkIcon(
            imageVector = icon,
            contentDescription = null,
            tint = if (enabled) iconTint else colors.disabledContentColor
        )
    }
}
