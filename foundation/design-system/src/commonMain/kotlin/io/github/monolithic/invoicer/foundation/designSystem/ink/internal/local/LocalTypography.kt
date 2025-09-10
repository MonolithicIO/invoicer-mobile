package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography

internal val LocalInkTypography =
    staticCompositionLocalOf<InkTypography> {
        error(
            "No typography provided through " +
                    "composition local. Check your theme initialization for missing assignments"
        )
    }
