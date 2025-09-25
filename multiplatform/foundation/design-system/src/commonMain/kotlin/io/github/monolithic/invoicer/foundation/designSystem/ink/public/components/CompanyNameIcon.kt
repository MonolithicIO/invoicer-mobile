package io.github.monolithic.invoicer.foundation.designSystem.ink.public.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.utils.modifier.circleLayout

@Composable
fun CompanyNameIcon(
    name: String,
    modifier: Modifier = Modifier
) {
    val backgroundColor = InkTheme.colorScheme.onBackground
    InkText(
        modifier = modifier
            .background(backgroundColor, shape = CircleShape)
            .padding(InkTheme.spacing.xSmall2)
            .circleLayout(),
        text = name.take(2).toUpperCase(Locale.current),
        color = InkTheme.colorScheme.background,
        weight = FontWeight.Bold,
        style = InkTextStyle.Heading5
    )
}
