package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor

@Composable
fun InkText(
    text: String,
    modifier: Modifier = Modifier,
    style: InkTextStyle = InkTextStyle.BodyMedium,
    color: InkColor = LocalInkColorScheme.current.onBackground,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
) {
    BasicText(
        style = typographyFromStyle(style).merge(
            textAlign = textAlign ?: TextAlign.Unspecified,
            color = color.value
        ),
        text = text,
        modifier = modifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout
    )
}

@Composable
fun InkText(
    text: AnnotatedString,
    modifier: Modifier = Modifier,
    style: InkTextStyle = InkTextStyle.BodyMedium,
    textAlign: TextAlign? = null,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: ((TextLayoutResult) -> Unit)? = null,
    color: InkColor = LocalInkColorScheme.current.onBackground,
) {
    BasicText(
        style = typographyFromStyle(style).merge(
            textAlign = textAlign ?: TextAlign.Unspecified,
            color = color.value
        ),
        text = text,
        modifier = modifier,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        minLines = minLines,
        onTextLayout = onTextLayout,
    )
}

@Composable
private fun typographyFromStyle(style: InkTextStyle): TextStyle {
    val localTypography = LocalInkTypography.current

    return when (style) {
        InkTextStyle.Heading1 -> localTypography.h1
        InkTextStyle.Heading2 -> localTypography.h2
        InkTextStyle.Heading3 -> localTypography.h3
        InkTextStyle.Heading4 -> localTypography.h4
        InkTextStyle.Heading5 -> localTypography.h5
        InkTextStyle.Heading6 -> localTypography.h6
        InkTextStyle.BodyXlarge -> localTypography.bodyXLarge
        InkTextStyle.BodyLarge -> localTypography.bodyLarge
        InkTextStyle.BodyMedium -> localTypography.bodyMedium
        InkTextStyle.BodySmall -> localTypography.bodySmall
        InkTextStyle.BodyXSmall -> localTypography.bodyXSmall
    }
}

enum class InkTextStyle {
    Heading1,
    Heading2,
    Heading3,
    Heading4,
    Heading5,
    Heading6,
    BodyXlarge,
    BodyLarge,
    BodyMedium,
    BodySmall,
    BodyXSmall,
}
