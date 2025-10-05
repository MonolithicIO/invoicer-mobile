package io.github.monolithic.invoicer.features.invoice.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import io.github.monolithic.invoicer.features.invoice.presentation.screens.feedback.InvoiceFeedbackScreen
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme.InvoicerInkTheme

@Composable
@Preview
private fun Preview() {
    InvoicerInkTheme {
        InvoiceFeedbackScreen().StateContent { }
    }
}
