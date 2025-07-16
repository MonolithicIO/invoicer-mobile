package io.github.monolithic.invoicer.foundation.designSystem.components.preview

import androidx.compose.runtime.Composable
import io.github.monolithic.invoicer.foundation.designSystem.theme.InvoicerTheme

@Composable
fun ThemeContainer(
    content: @Composable () -> Unit
) {
    InvoicerTheme {
        content()
    }
}
