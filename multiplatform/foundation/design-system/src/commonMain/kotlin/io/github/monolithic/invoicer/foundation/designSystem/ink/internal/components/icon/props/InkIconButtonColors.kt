package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.props

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults.colors

data class InkIconButtonColors(
    val containerColor: Color,
    val disabledContainerColor: Color,
    val iconColor: Color,
    val disabledIconColor: Color,
) {
    @Composable
    fun backgroundColor(enabled: Boolean): Color {
        val colors = colors
        return if (enabled) colors.containerColor else colors.disabledContainerColor
    }
}
