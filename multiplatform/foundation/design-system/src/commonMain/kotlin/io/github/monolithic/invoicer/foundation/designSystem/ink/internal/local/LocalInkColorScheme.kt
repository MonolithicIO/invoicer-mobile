package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.lightInkColorScheme

internal val LocalInkColorScheme =
    staticCompositionLocalOf { lightInkColorScheme() }
