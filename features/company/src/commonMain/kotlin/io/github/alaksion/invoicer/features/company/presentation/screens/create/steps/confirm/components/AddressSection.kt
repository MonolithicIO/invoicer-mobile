package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.create_company_confirmation_address_line_2_label
import invoicer.features.company.generated.resources.create_company_confirmation_address_line_label
import invoicer.features.company.generated.resources.create_company_confirmation_address_section_label
import invoicer.features.company.generated.resources.create_company_confirmation_city_label
import invoicer.features.company.generated.resources.create_company_confirmation_country_code_label
import invoicer.features.company.generated.resources.create_company_confirmation_postal_code_label
import invoicer.features.company.generated.resources.create_company_confirmation_state_label
import org.jetbrains.compose.resources.stringResource

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
        title = stringResource(Res.string.create_company_confirmation_address_section_label),
        modifier = modifier,
    ) {
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_address_line_label),
            value = addressLine1,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_address_line_2_label),
            value = addressLine2,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_city_label),
            value = city,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_state_label),
            value = state,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_postal_code_label),
            value = postalCode,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_country_code_label),
            value = countryCode,
        )
    }
}