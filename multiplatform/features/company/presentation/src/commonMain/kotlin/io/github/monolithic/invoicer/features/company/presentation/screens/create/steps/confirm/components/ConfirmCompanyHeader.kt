package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.RoundTextAbbreviation

@Composable
internal fun ConfirmCompanyHeader(
    companyName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.small),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        RoundTextAbbreviation(
            text = companyName
        )
        InkText(
            text = companyName,
            style = InkTextStyle.Heading5,
            weight = FontWeight.Bold
        )
    }
}