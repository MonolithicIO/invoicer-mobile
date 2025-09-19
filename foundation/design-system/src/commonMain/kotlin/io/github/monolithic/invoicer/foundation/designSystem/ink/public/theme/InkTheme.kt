package io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InternalInkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.darkInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.defaultInkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.defaultInkSpacing
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.lightInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.tokens.InkTypeFaces

@Composable
fun InkTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (isDarkTheme) {
        darkInkColorScheme()
    } else {
        lightInkColorScheme()
    }
    val typography = invoicerTypography()

    InternalInkTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
        isDarkTheme = isDarkTheme,
        spacing = defaultInkSpacing(),
        shape = defaultInkShape(),
    )
}

@Composable
private fun invoicerTypography() = InkTypography(
    h1 = InkTypeFaces.Headline.h1,
    h2 = InkTypeFaces.Headline.h2,
    h3 = InkTypeFaces.Headline.h3,
    h4 = InkTypeFaces.Headline.h4,
    h5 = InkTypeFaces.Headline.h5,
    h6 = InkTypeFaces.Headline.h6,
    bodyXLarge = InkTypeFaces.Body.xLarge,
    bodyLarge = InkTypeFaces.Body.large,
    bodyMedium = InkTypeFaces.Body.medium,
    bodySmall = InkTypeFaces.Body.small,
    bodyXSmall = InkTypeFaces.Body.xSmall,
)
