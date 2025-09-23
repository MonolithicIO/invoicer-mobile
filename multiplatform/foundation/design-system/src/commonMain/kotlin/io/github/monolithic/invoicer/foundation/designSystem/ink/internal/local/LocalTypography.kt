package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkTypography
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.defaultInkTypography

internal val LocalInkTypography =
    staticCompositionLocalOf<InkTypography> { defaultInkTypography() }
