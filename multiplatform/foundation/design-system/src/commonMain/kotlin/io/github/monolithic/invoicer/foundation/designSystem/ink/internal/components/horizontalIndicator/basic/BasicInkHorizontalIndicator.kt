package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.horizontalIndicator.basic

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

private val MinHeight = 4.dp
private val Radius = 20f

@Composable
internal fun BasicInkHorizontalIndicator(
    modifier: Modifier = Modifier,
    containerColor: Color = InkTheme.colorScheme.surfaceDark,
    progressColor: Color = InkTheme.colorScheme.primary,
    height: Dp = MinHeight,
    progress: Float = 0f,
) {
    val internalProgress = progress.coerceIn(minimumValue = 0f, maximumValue = 1f)

    Canvas(
        modifier = modifier
            .defaultMinSize(minHeight = MinHeight)
            .height(height)
    ) {
        drawRoundRect(
            color = containerColor,
            style = Fill,
            size = Size(width = this.size.width, height = this.size.height),
            cornerRadius = CornerRadius(Radius, Radius)
        )
        drawRoundRect(
            color = progressColor,
            style = Fill,
            size = Size(width = this.size.width * internalProgress, height = this.size.height),
            cornerRadius = CornerRadius(Radius, Radius)
        )
    }
}