package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.company.presentation.generated.resources.Res
import invoicer.features.company.presentation.generated.resources.create_company_confirmation_document_label
import invoicer.features.company.presentation.generated.resources.create_company_confirmation_info_section_label
import invoicer.features.company.presentation.generated.resources.create_company_confirmation_name_label
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CompanySection(
    name: String,
    document: String,
    modifier: Modifier = Modifier
) {
    ConfirmCompanySection(
        title = stringResource(Res.string.create_company_confirmation_info_section_label),
        modifier = modifier,
    ) {
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_name_label),
            value = name,
        )
        ConfirmCompanyField(
            label = stringResource(Res.string.create_company_confirmation_document_label),
            value = document,
        )
    }
}
