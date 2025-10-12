package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props

import androidx.compose.ui.graphics.Color

internal data class InkInputColors(
    val indicator: Color,
    val background: Color,
    // Focused
    val focusedIndicator: Color,
    // Disabled
    val disabledIndicator: Color,
    val disabledBackground: Color,
    val disabledText: Color,
    val disabledLabel: Color,
    // Error
    val errorIndicator: Color,
    val errorText: Color,
    val errorCursor: Color,
    val errorLabel: Color,
    val errorPlaceholder: Color,
    // Common
    val placeholderColor: Color,
    val labelColor: Color,
    val textColor: Color,
    val cursorColor: Color,
    val supportTextColor: Color,
)
