package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkSpacing
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColorScheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkSpacing
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
}
