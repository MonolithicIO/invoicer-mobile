package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
private fun OutlinedInputPreview() {
    var text by remember() { mutableStateOf("awdawd") }
    InkTheme {
        Column(
            modifier = Modifier
                .background(InkTheme.colorScheme.background.value)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InkOutlinedInput(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = { },
                placeholder = "hello 2",
                label = "hello world"
            )

            InkOutlinedInput(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it }
            )

            InkOutlinedInput(
                modifier = Modifier.fillMaxWidth(),
                value = text,
                onValueChange = { text = it },
                isError = true
            )

            InkOutlinedInput(
                modifier = Modifier.fillMaxWidth(),
                value = "addwadwadaw",
                onValueChange = { text = it },
                enabled = false
            )
        }
    }
}