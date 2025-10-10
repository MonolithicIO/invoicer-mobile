package io.github.monolithic.invoicer.features.company.presentation.screens.select.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_user
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.SelectableCard
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SelectCompanyCard(
    isSelected: Boolean,
    companyName: String,
    companyDocument: String,
    onSelect: () -> Unit,
    modifier: Modifier = Modifier,
) {
    SelectableCard(
        isSelected = isSelected,
        onSelect = onSelect,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            InkIcon(
                painter = painterResource(DsResources.drawable.ic_user),
                contentDescription = null,
                tint = InkTheme.colorScheme.onBackground
            )
            Column {
                InkText(
                    text = companyName,
                    style = InkTextStyle.BodyLarge,
                    weight = FontWeight.SemiBold
                )
                InkText(
                    text = companyDocument,
                    style = InkTextStyle.BodyMedium,
                    color = InkTheme.colorScheme.onBackgroundVariant
                )
            }
        }
    }
}