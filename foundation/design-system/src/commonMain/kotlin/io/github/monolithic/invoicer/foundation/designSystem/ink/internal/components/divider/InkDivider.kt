package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkHorizontalDivider(
    color: Color = InkTheme.colorScheme.disabled,
    thickness: InkDividerThickness = InkDividerThickness.Small,
    modifier: Modifier = Modifier
) {
    Canvas(modifier.fillMaxWidth().height(thickness.value)) {
        drawLine(
            color = color,
            strokeWidth = thickness.value.toPx(),
            start = Offset(0f, thickness.value.toPx() / 2),
            end = Offset(size.width, thickness.value.toPx() / 2),
        )
    }
}

@Composable
fun InkVerticalDivider(
    color: Color = InkTheme.colorScheme.disabled,
    thickness: InkDividerThickness = InkDividerThickness.Small,
    modifier: Modifier = Modifier
) {
    Canvas(modifier.fillMaxHeight().width(thickness.value)) {
        drawLine(
            color = color,
            strokeWidth = thickness.value.toPx(),
            start = Offset(thickness.value.toPx() / 2, 0f),
            end = Offset(thickness.value.toPx() / 2, size.height),
        )
    }
}

enum class InkDividerThickness(
    internal val value: Dp
) {
    Small(1.dp),
    Medium(2.dp),
    Large(4.dp);
}
