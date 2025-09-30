package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.PreviewContainer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.horizontalIndicator.InkHorizontalIndicator

@Composable
@Preview
private fun HorizontalIndicatorPreview() {
    PreviewContainer {
        InkHorizontalIndicator(
            modifier = Modifier.fillMaxWidth(),
            height = 20.dp
        )
        InkHorizontalIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = 0.5f,
        )
    }
}
