package io.github.monolithic.invoicer.foundation.designSystem.legacy.components.preview

import androidx.compose.runtime.Composable
import io.github.monolithic.invoicer.foundation.designSystem.legacy.theme.InvoicerTheme

@Composable
fun ThemeContainer(
    content: @Composable () -> Unit
) {
    InvoicerTheme {
        content()
    }
}
