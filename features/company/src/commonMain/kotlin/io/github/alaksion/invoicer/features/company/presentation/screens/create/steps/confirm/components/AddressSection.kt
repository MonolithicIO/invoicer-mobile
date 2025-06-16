package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal fun AddressSection(
    addressLine1: String,
    addressLine2: String,
    city: String,
    state: String,
    postalCode: String,
    countryCode: String,
    modifier: Modifier = Modifier
) {
    ConfirmCompanySection(
        title = "Address",
        modifier = modifier,
    ) {
        ConfirmCompanyField(
            label = "Address Line 1",
            value = addressLine1,
        )
        ConfirmCompanyField(
            label = "Address Line 2",
            value = addressLine2,
        )
        ConfirmCompanyField(
            label = "City",
            value = city,
        )
        ConfirmCompanyField(
            label = "State",
            value = state,
        )
        ConfirmCompanyField(
            label = "Postal Code",
            value = postalCode,
        )
        ConfirmCompanyField(
            label = "Country Code",
            value = countryCode,
        )
    }
}