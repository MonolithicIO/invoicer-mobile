package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_address_line_2_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_address_line_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_city_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_country_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_postal_code_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_state_label
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LabeledListItem
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ColumnScope.AddressSection(
    addressLine1: String,
    addressLine2: String,
    city: String,
    state: String,
    postalCode: String,
    countryCode: String,
) {
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_address_line_label),
        value = addressLine1,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_address_line_2_label),
        value = addressLine2,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_city_label),
        value = city,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_state_label),
        value = state,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_postal_code_label),
        value = postalCode,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_country_code_label),
        value = countryCode,
    )
}
