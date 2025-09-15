package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs

import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

data class InkColorScheme(
    val background: InkColor,
    val onBackground: InkColor,
    val onBackgroundVariant: InkColor,
    val primary: InkColor,
    val onPrimary: InkColor,
    val disabled: InkColor,
    val onDisabled: InkColor,
    val surface: InkColor,
    val onSurface: InkColor,
    val surfaceVariant: InkColor,
    val onSurfaceVariant: InkColor,
    val borderStroke: InkColor,
    val textSurface: InkColor,
    val textSurfaceLight: InkColor,
    val error: InkColor,
    val errorBackground: InkColor,
    val none: InkColor = InkColor(Color.Transparent),
)

@JvmInline
value class InkColor(val value: Color) {
    fun copy(alpha: Float) = InkColor(value.copy(alpha = alpha))
}
