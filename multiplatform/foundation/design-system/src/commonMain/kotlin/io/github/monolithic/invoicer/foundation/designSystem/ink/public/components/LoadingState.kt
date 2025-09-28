package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator

@Composable
fun LoadingState(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        InkCircularIndicator()
    }
}
