package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs

import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

data class InkColorScheme(
    val background: InkColor,
    val onBackground: InkColor,
)

@JvmInline
value class InkColor(val value: Color)
