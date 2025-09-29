package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkSurface(
    modifier: Modifier = Modifier,
    color: Color = InkTheme.colorScheme.background,
    shape: Shape = RectangleShape,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color
    ) {
        content()
    }
}

@Composable
fun InkSurface(
    modifier: Modifier = Modifier,
    color: Color = InkTheme.colorScheme.background,
    shape: Shape = RectangleShape,
    onClick: () -> Unit,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = color,
        onClick = onClick,
        enabled = enabled,
        interactionSource = interactionSource
    ) {
        content()
    }
}
