package io.github.monolithic.invoicer.foundation.designSystem.ink

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InvoicerInkTheme

@Composable
internal fun PreviewContainer(
    isDarkMode: Boolean = false,
    content: @Composable () -> Unit,
) {
    InvoicerInkTheme(isDarkTheme = isDarkMode) {
        Column(
            modifier = Modifier
                .background(InkTheme.colorScheme.background)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            content()
        }
    }
}
