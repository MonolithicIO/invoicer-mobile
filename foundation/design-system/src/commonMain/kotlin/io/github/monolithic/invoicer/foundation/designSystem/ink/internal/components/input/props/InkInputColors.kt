package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props

import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor

internal data class InkInputColors(
    // Focused
    val focusedIndicator: InkColor,
    val focusedBackground: InkColor,
    // Unfocused
    val unfocusedIndicator: InkColor,
    val unfocusedBackground: InkColor,
    // Disabled
    val disabledIndicator: InkColor,
    val disabledBackground: InkColor,
    val disabledText: InkColor,
    // Error
    val errorIndicator: InkColor,
    val errorBackground: InkColor,
    val errorText: InkColor,
    val errorCursor: InkColor,
    val errorLabel: InkColor,
    // Common
    val placeholderColor: InkColor,
    val labelColor: InkColor,
    val textColor: InkColor,
    val cursorColor: InkColor
)
