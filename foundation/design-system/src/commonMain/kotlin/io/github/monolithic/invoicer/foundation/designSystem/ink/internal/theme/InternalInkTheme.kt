package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography

@Composable
internal fun InternalInkTheme(
    colorScheme: InkColorScheme,
    typography: InkTypography,
    content: @Composable () -> Unit,
) {
    CompositionLocalProvider(
        LocalInkColorScheme provides colorScheme,
        LocalInkTypography provides typography
    ) {
        content()
    }
}
