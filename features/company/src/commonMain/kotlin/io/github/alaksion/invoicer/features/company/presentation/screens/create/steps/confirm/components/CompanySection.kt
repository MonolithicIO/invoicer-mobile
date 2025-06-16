package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun CompanySection(
    name: String,
    document: String,
    modifier: Modifier = Modifier
) {
    ConfirmCompanySection(
        title = "Company Information",
        modifier = modifier,
    ) {
        ConfirmCompanyField(
            label = "Name",
            value = name,
        )
        ConfirmCompanyField(
            label = "Document",
            value = document,
        )
    }
}