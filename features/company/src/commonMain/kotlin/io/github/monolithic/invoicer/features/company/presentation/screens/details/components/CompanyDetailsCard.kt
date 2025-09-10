package io.github.monolithic.invoicer.features.company.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.company.generated.resources.Res
import invoicer.features.company.generated.resources.company_details_edit
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.HorizontalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CompanyDetailsCard(
    modifier: Modifier = Modifier,
    title: String,
    onEditClick: (() -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
) {
    Card(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.small),
            verticalArrangement = Arrangement.spacedBy(Spacing.small)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineMedium
            )
            content()
            onEditClick?.let {
                TextButton(
                    onClick = it
                ) {
                    Icon(
                        imageVector = Icons.Outlined.Edit,
                        contentDescription = null
                    )
                    HorizontalSpacer(SpacerSize.Small)
                    Text(text = stringResource(Res.string.company_details_edit))
                }
            }
        }
    }
}
