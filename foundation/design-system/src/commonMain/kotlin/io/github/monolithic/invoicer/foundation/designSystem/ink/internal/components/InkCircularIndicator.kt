package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkCircularIndicator(
    modifier: Modifier = Modifier,
    color: InkColor = InkTheme.colorScheme.brand
) {
    CircularProgressIndicator(
        color = color.value,
        modifier = modifier
    )
}