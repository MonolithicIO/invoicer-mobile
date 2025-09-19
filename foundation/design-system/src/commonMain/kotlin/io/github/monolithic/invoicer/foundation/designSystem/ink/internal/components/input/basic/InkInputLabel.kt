package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputDefaults

@Composable
internal fun InkInputLabel(
    text: String,
    isError: Boolean,
    isEnabled: Boolean,
    modifier: Modifier = Modifier
) {
    val labelColor by InkInputDefaults.labelColor(isError = isError, isEnabled = isEnabled)

    InkText(
        modifier = modifier,
        text = text,
        style = InkTextStyle.BodyXlarge,
        weight = FontWeight.SemiBold,
        color = labelColor,
        maxLines = 1
    )
}
