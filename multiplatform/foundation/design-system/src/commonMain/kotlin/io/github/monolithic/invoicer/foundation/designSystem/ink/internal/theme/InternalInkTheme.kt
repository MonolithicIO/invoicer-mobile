package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalDarkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkSpacing
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkSpacing
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.tokens.InkColors

@Composable
internal fun InternalInkTheme(
    colorScheme: InkColorScheme,
    typography: InkTypography,
    shape: InkShape,
    spacing: InkSpacing,
    isDarkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalInkColorScheme provides colorScheme,
        LocalInkTypography provides typography,
        LocalDarkTheme provides isDarkTheme,
        LocalInkShape provides shape,
        LocalInkSpacing provides spacing
    ) {
        content()
    }
}

fun lightInkColorScheme(): InkColorScheme {
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
        surfaceLight = InkColors.GreyScale.grey100,
        surfaceDark = InkColors.GreyScale.grey200,
        primaryVariant = InkColors.Transparent.transparentPrimary,
    )
}

fun darkInkColorScheme(): InkColorScheme {
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
        primaryVariant = InkColors.Transparent.transparentPrimary
    )
}

fun defaultInkTypography() = InkTypography(
    h1 = TextStyle(fontSize = 48.sp),
    h2 = TextStyle(fontSize = 48.sp),
    h3 = TextStyle(fontSize = 32.sp),
    h4 = TextStyle(fontSize = 24.sp),
    h5 = TextStyle(fontSize = 20.sp),
    h6 = TextStyle(fontSize = 18.sp),
    bodyXLarge = TextStyle(fontSize = 24.sp),
    bodyLarge = TextStyle(fontSize = 20.sp),
    bodyMedium = TextStyle(fontSize = 16.sp),
    bodySmall = TextStyle(fontSize = 12.sp),
    bodyXSmall = TextStyle(fontSize = 8.sp),
)

@Suppress("MagicNumber")
fun defaultInkShape() = InkShape(
    regular = RoundedCornerShape(14.dp),
    small = RoundedCornerShape(8.dp),
    large = RoundedCornerShape(20.dp),
    pill = RoundedCornerShape(100),
)

fun defaultInkSpacing() = InkSpacing(
    xSmall3 = 2.dp,
    xSmall2 = 4.dp,
    xSmall = 8.dp,
    small = 12.dp,
    medium = 16.dp,
    large = 20.dp,
    xLarge = 24.dp,
    xLarge2 = 28.dp,
    xLarge3 = 32.dp,
)

object InkTheme {
    val colorScheme: InkColorScheme
        @Composable
        get() = LocalInkColorScheme.current

    val typography: InkTypography
        @Composable
        get() = LocalInkTypography.current

    val shape: InkShape
        @Composable
        get() = LocalInkShape.current

    val spacing: InkSpacing
        @Composable
        get() = LocalInkSpacing.current

    val isDark: Boolean
        @Composable
        get() = LocalDarkTheme.current
}
