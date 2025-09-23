package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkCircularIndicator(
    modifier: Modifier = Modifier,
    color: Color = InkTheme.colorScheme.primary
) {
    CircularProgressIndicator(
        color = color,
        modifier = modifier
    )
}
