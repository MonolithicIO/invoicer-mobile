package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs

import androidx.compose.ui.graphics.Color

data class InkColorScheme(
    val background: InkColor,
    val onBackground: InkColor,
)

value class InkColor(val value: Color)
