package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_bank_address_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_bank_name_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_iban_label
import invoicer.multiplatform.features.company.presentation.generated.resources.create_company_confirmation_swift_label
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LabeledListItem
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun ColumnScope.PaySection(
    iban: String,
    swift: String,
    bankName: String,
    bankAddress: String,
) {
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_swift_label),
        value = swift,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_iban_label),
        value = iban,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_bank_name_label),
        value = bankName,
    )
    LabeledListItem(
        label = stringResource(Res.string.create_company_confirmation_bank_address_label),
        value = bankAddress,
    )
}
