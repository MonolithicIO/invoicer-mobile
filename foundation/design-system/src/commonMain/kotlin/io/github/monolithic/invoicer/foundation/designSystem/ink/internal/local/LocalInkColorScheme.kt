package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColorScheme

internal val LocalInkColorScheme =
    staticCompositionLocalOf<InkColorScheme> {
        error("No color scheme provided by composition local. Check your theme definition for missing assignments")
    }
