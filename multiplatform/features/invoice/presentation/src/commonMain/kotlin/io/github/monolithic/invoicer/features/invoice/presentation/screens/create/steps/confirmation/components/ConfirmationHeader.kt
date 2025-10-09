package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.RoundTextAbbreviation

@Composable
internal fun ConfirmationHeader(
    customerName: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        RoundTextAbbreviation(
            text = customerName
        )
        InkText(
            text = customerName,
            style = InkTextStyle.Heading5,
            weight = FontWeight.Bold
        )
    }
}
