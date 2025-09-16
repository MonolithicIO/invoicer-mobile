package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props

import androidx.compose.ui.graphics.Color

internal data class InkInputColors(
    // Focused
    val focusedIndicator: Color,
    val focusedBackground: Color,
    // Unfocused
    val unfocusedIndicator: Color,
    val unfocusedBackground: Color,
    // Disabled
    val disabledIndicator: Color,
    val disabledBackground: Color,
    val disabledText: Color,
    val disabledLabel: Color,
    // Error
    val errorIndicator: Color,
    val errorBackground: Color,
    val errorText: Color,
    val errorCursor: Color,
    val errorLabel: Color,
    // Common
    val placeholderColor: Color,
    val labelColor: Color,
    val textColor: Color,
    val cursorColor: Color
)
