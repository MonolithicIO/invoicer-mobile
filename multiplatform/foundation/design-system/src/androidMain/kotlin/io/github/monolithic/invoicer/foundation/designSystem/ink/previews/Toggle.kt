package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import io.github.monolithic.invoicer.foundation.designSystem.ink.PreviewContainer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.toggle.InkToggle

@Composable
@Preview
fun TogglePreview() {
    PreviewContainer(true) {
        var checkedState by remember { mutableStateOf(false) }

        InkToggle(
            checked = checkedState,
            onClick = { checkedState = it },
        )

        InkToggle(
            checked = checkedState,
            onClick = { checkedState = it },
        )

        InkToggle(
            checked = checkedState,
            enabled = false,
            onClick = { checkedState = it },
        )
    }
}
