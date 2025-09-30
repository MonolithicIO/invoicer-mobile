package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkHorizontalIndicator(
    modifier: Modifier = Modifier,
    gapSize: Dp = 4.0.dp
) {
    LinearProgressIndicator(
        modifier = modifier,
        color = InkTheme.colorScheme.primary,
        trackColor = InkTheme.colorScheme.surfaceLight,
        gapSize = gapSize,
    )
}

@Composable
fun InkHorizontalIndicator(
    modifier: Modifier = Modifier,
    gapSize: Dp = 4.0.dp,
    progress: Float = 0f,
    animated: Boolean = false,
) {
    val internalProgress = if (animated) {
        animateFloatAsState(
            targetValue = progress
        ).value
    } else progress

    LinearProgressIndicator(
        progress = { internalProgress },
        modifier = modifier,
        color = InkTheme.colorScheme.primary,
        trackColor = InkTheme.colorScheme.surfaceLight,
        gapSize = gapSize,
    )
}
