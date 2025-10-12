package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun Title(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall)
    ) {
        InkText(
            text = title,
            style = InkTextStyle.Heading3,
            weight = FontWeight.Bold
        )
        InkText(
            text = subtitle,
            style = InkTextStyle.BodyMedium,
            color = InkTheme.colorScheme.onBackgroundVariant
        )
    }
}