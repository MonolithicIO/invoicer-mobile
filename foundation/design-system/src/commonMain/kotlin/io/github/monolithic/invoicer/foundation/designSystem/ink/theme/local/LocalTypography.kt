package io.github.monolithic.invoicer.foundation.designSystem.ink.theme.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.values.InkTypography

internal val LocalInkTypography =
    staticCompositionLocalOf<InkTypography> { error("No typography group provided") }