package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputDefaults

@Composable
internal fun InkInputPlaceHolder(
    isError: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    InkText(
        text = text,
        modifier = modifier,
        style = InkTextStyle.BodyMedium,
        color = InkInputDefaults.placeholderColor(isError = isError),
        maxLines = 1
    )
}
