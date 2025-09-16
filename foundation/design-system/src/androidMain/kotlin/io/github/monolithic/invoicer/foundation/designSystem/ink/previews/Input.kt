package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InkTheme

@Composable
@Preview
private fun InkOutlinedInputPreview() {
    var text by remember { mutableStateOf("") }

    InkTheme(false) {
        Column(
            modifier = Modifier
                .background(InkTheme.colorScheme.background)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InkOutlinedInput(
                label = "Label 1",
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Placeholder Text1"
            )
            InkOutlinedInput(
                label = "Label 2",
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                placeholder = "Placeholder Text2"
            )
            InkOutlinedInput(
                label = "Label Error",
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                isError = true,
                placeholder = "Placeholder Error"
            )
            InkOutlinedInput(
                label = "Label Disabled",
                value = text,
                onValueChange = { text = it },
                modifier = Modifier.fillMaxWidth(),
                isEnabled = false,
                placeholder = "Placeholder Disabled"
            )
        }
    }
}