package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle

@Composable
internal fun InkInputLabel(
    text: String,
    color: Color,
    modifier: Modifier = Modifier
) {
    InkText(
        modifier = modifier,
        text = text,
        style = InkTextStyle.BodyXlarge,
        weight = FontWeight.SemiBold,
        color = color,
        maxLines = 1
    )
}