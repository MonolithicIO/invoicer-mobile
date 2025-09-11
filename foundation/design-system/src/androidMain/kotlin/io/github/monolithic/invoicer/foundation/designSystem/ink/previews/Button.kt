package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InkTheme

@Composable
@Preview
private fun PrimaryPreview() {
    InkTheme(false) {
        Column(
            modifier = Modifier
                .background(InkTheme.colorScheme.background.value)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            InkPrimaryButton(
                text = "awdwa",
                loading = false
            ) {

            }

            InkSecondaryButton(
                text = "awdwa",
                loading = false
            ) {

            }
        }

    }
}