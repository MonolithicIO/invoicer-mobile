package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun PaySection(
    title: String,
    iban: String,
    swift: String,
    bankName: String,
    bankAddress: String,
    modifier: Modifier = Modifier
) {
    ConfirmCompanySection(
        title = title,
        modifier = modifier,
    ) {
        ConfirmCompanyField(
            label = "Swift",
            value = swift,
        )
        ConfirmCompanyField(
            label = "Iban",
            value = iban,
        )
        ConfirmCompanyField(
            label = "Bank name",
            value = bankName,
        )
        ConfirmCompanyField(
            label = "Bank Address",
            value = bankAddress,
        )
    }
}