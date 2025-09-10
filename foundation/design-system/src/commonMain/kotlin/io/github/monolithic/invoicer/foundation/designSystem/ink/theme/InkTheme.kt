package io.github.monolithic.invoicer.foundation.designSystem.ink.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.monolithic.invoicer.foundation.designSystem.ink.theme.local.LocalInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.theme.local.LocalInkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.values.darkInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.values.inkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.values.lightInkColorScheme

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

    CompositionLocalProvider(
        LocalInkColorScheme provides colorScheme,
        LocalInkTypography provides typography
    ) {
        content()
    }
}