package io.github.monolithic.invoicer.features.invoice.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.Spacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
internal fun InvoiceDetailsRow(
    label: String,
    value: String,
    valueStyle: InkTextStyle = InkTextStyle.BodyXlarge,
    valueColor: Color = InkTheme.colorScheme.onBackground,
    valueWeight: FontWeight = FontWeight.SemiBold,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        InkText(
            text = label,
            style = InkTextStyle.BodyMedium
        )
        Spacer(1f)
        InkText(
            text = value,
            style = valueStyle,
            color = valueColor,
            weight = valueWeight
        )
    }
}
