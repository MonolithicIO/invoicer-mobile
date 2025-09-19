package io.github.monolithic.invoicer.foundation.designSystem.legacy.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing


@Composable
fun TextDivider(
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        InkHorizontalDivider(modifier = Modifier.weight(1f))
        InkText(
            text = text,
            style = InkTextStyle.BodyMedium,
            color = InkTheme.colorScheme.onBackground
        )
        InkHorizontalDivider(modifier = Modifier.weight(1f))
    }
}
