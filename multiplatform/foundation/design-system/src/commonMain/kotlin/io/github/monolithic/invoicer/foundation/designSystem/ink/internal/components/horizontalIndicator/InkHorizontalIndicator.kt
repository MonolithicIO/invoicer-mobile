package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.horizontalIndicator

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.horizontalIndicator.basic.BasicInkHorizontalIndicator

private val DefaultHeight = 4.dp

@Composable
fun InkHorizontalIndicator(
    modifier: Modifier = Modifier,
    progress: Float = 0f,
    height: Dp = DefaultHeight,
    animated: Boolean = false,
) {
    val internalProgress = if (animated) {
        animateFloatAsState(
            targetValue = progress
        ).value
    } else progress


    BasicInkHorizontalIndicator(
        modifier = modifier,
        progress = internalProgress,
        height = height
    )
}

