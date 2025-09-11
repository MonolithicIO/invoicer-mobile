package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs

import androidx.compose.ui.graphics.Color
import kotlin.jvm.JvmInline

data class InkColorScheme(
    val background: InkColor,
    val onBackground: InkColor,
    val primary: InkColor,
    val onPrimary: InkColor,
    val disabled: InkColor,
    val onDisabled: InkColor,
    val surface: InkColor,
    val onSurface: InkColor,
)
data class InkButtonsColorScheme(
    val primaryBackground: InkColor,
    val primaryContent: InkColor,
    val primaryDisabledBackground: InkColor,
    val primaryDisabledContent: InkColor,
    val primaryLoadingIndicator: InkColor,
    val secondaryBackground: InkColor,
    val secondaryContent: InkColor,
    val secondaryDisabledBackground: InkColor,
    val secondaryDisabledContent: InkColor,
    val secondaryLoadingIndicator: InkColor,
)

@JvmInline
value class InkColor(val value: Color) {
    fun copy(alpha: Float) = InkColor(value.copy(alpha = alpha))
}
