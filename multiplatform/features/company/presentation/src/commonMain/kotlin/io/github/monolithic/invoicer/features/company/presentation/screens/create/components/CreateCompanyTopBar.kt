package io.github.monolithic.invoicer.features.company.presentation.screens.create.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.ProgressTopBar

private const val CreateCompanyMaxStep = 3

@Composable
internal fun CreateCompanyTopBar(
    modifier: Modifier = Modifier,
    onBack: () -> Unit,
    step: Int
) {
    ProgressTopBar(
        step = step,
        maxStep = CreateCompanyMaxStep,
        modifier = modifier,
        onBack = onBack
    )
}