package io.github.monolithic.invoicer.features.company.presentation.screens.select.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_confirm_cta
import invoicer.multiplatform.features.company.presentation.generated.resources.company_selection_new_company_cta
import io.github.monolithic.invoicer.features.company.presentation.screens.select.SelectCompanyMode
import io.github.monolithic.invoicer.features.company.presentation.screens.select.SelectCompanyState
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkSecondaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun SelectCompanyBottomBar(
    state: SelectCompanyState,
    modifier: Modifier = Modifier,
    onAction: (SelectCompanyBottomBarAction) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        when (state.mode) {
            SelectCompanyMode.Loading, SelectCompanyMode.Error -> Box(Modifier)
            SelectCompanyMode.List -> {
                InkPrimaryButton(
                    text = stringResource(Res.string.company_selection_confirm_cta),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(SelectCompanyBottomBarAction.Continue)
                    },
                )
                InkSecondaryButton(
                    text = stringResource(Res.string.company_selection_new_company_cta),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(SelectCompanyBottomBarAction.CreateNew)
                    }
                )

            }

            SelectCompanyMode.EmptyState -> {
                InkPrimaryButton(
                    text = stringResource(Res.string.company_selection_confirm_cta),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {
                        onAction(SelectCompanyBottomBarAction.Continue)
                    }
                )
            }
        }
    }
}

internal enum class SelectCompanyBottomBarAction {
    Continue,
    CreateNew;
}
