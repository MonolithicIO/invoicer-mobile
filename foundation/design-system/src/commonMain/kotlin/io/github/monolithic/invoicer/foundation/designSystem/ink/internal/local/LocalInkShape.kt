package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local

import androidx.compose.runtime.staticCompositionLocalOf
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.defaultInkShape

val LocalInkShape = staticCompositionLocalOf<InkShape> { defaultInkShape() }
