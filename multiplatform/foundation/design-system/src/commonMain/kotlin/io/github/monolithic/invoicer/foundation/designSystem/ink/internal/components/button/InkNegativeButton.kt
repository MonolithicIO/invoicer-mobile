package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkNegativeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: InkButtonSize = InkButtonSize.Regular,
) {
    InkBasicButton(
        onClick = onClick,
        modifier = modifier,
        enabled = true,
        size = size,
        backgroundColor = InkTheme.colorScheme.error,
        contentColor = InkTheme.colorScheme.white,
        borderStroke = null,
    ) {
        InkText(
            text = text,
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Bold,
            color = InkTheme.colorScheme.white
        )
    }
}
