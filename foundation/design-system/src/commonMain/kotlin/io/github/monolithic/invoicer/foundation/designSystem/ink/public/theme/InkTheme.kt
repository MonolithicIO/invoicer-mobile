package io.github.monolithic.invoicer.foundation.designSystem.ink.public.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InternalInkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.tokens.InkColors
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
    val typography = inkTypography()

    InternalInkTheme(
        colorScheme = colorScheme,
        typography = typography,
        content = content,
        isDarkTheme = isDarkTheme
    )
}

private fun lightInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.BlackWhite.white,
        onBackground = InkColors.GreyScale.grey900,
        primary = InkColors.Main.primary,
        onPrimary = InkColors.BlackWhite.white,
        disabled = InkColors.AlertStatus.lightDisabled,
        onDisabled = InkColors.GreyScale.grey500,
        surface = InkColors.Background.backgroundPrimary,
        onSurface = InkColors.Main.primary,
        borderStroke = InkColors.GreyScale.grey200,
        surfaceVariant = InkColors.BlackWhite.white,
        onSurfaceVariant = InkColors.GreyScale.grey900,
        onBackgroundVariant = InkColors.GreyScale.grey700,
        error = InkColors.AlertStatus.error,
        errorBackground = InkColors.Background.red,
        surfaceLight = InkColors.GreyScale.grey50,
        surfaceDark = InkColors.GreyScale.grey200,
    )
}

private fun darkInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.Dark.dark1,
        onBackground = InkColors.BlackWhite.white,
        primary = InkColors.Main.primary,
        onPrimary = InkColors.BlackWhite.white,
        disabled = InkColors.AlertStatus.darkDisabled,
        onDisabled = InkColors.GreyScale.grey200,
        surface = InkColors.Dark.dark5,
        onSurface = InkColors.BlackWhite.white,
        borderStroke = InkColors.Dark.dark5,
        surfaceVariant = InkColors.Dark.dark4,
        onSurfaceVariant = InkColors.BlackWhite.white,
        onBackgroundVariant = InkColors.GreyScale.grey200,
        error = InkColors.AlertStatus.error,
        errorBackground = InkColors.Background.red,
        surfaceLight = InkColors.Dark.dark4,
        surfaceDark = InkColors.Dark.dark4,
    )
}

@Composable
private fun inkTypography() = InkTypography(
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
