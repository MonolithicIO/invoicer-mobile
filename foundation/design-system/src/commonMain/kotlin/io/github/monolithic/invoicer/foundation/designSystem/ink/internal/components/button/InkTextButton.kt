package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkTextButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: InkTextButtonColors = InkTextButtonDefaults.colors
) {
    InkBasicButton(
        onClick = onClick,
        backgroundColor = colors.backgroundColor,
        contentColor = colors.contentColor,
        modifier = modifier
    ) {
        InkText(
            text = text,
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Bold,
            color = colors.contentColor
        )
    }
}

@Composable
fun InkTextButton(
    text: AnnotatedString,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    colors: InkTextButtonColors = InkTextButtonDefaults.colors
) {
    InkBasicButton(
        onClick = onClick,
        backgroundColor = colors.backgroundColor,
        contentColor = colors.contentColor,
        modifier = modifier
    ) {
        InkText(
            text = text,
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Bold,
            color = colors.contentColor
        )
    }
}

data class InkTextButtonColors(
    val backgroundColor: Color,
    val contentColor: Color,
)

object InkTextButtonDefaults {
    val colors: InkTextButtonColors
        @Composable get() = InkTextButtonColors(
            backgroundColor = InkTheme.colorScheme.background,
            contentColor = InkTheme.colorScheme.primary
        )
}