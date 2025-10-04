package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ProgressTopBar


private const val CreateInvoiceMaxStep = 4

@Composable
internal fun CreateInvoiceToolbar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    step: Int
) {
    ProgressTopBar(
        step = step,
        maxStep = CreateInvoiceMaxStep,
        modifier = modifier,
        onBack = onBack
    )
}
