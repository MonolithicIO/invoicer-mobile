package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkNegativeSubtleButton(
    text: String,
    modifier: Modifier = Modifier,
    size: InkButtonSize = InkButtonSize.Regular,
    onClick: () -> Unit,
) {
    InkBasicButton(
        onClick = onClick,
        modifier = modifier,
        enabled = true,
        size = size,
        backgroundColor = InkTheme.colorScheme.errorBackground,
        contentColor = InkTheme.colorScheme.error,
        borderStroke = BorderStroke(width = 1.dp, color = InkTheme.colorScheme.error),
    ) {
        InkText(
            text = text,
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Bold,
            color = InkTheme.colorScheme.error
        )
    }
}