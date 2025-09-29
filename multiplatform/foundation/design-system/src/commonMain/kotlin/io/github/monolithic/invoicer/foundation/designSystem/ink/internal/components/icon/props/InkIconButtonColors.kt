package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.props

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class InkIconButtonColors(
    val containerColor: Color,
    val disabledContainerColor: Color,
    val iconColor: Color,
    val disabledIconColor: Color,
) {
    @Composable
    fun backgroundColor(enabled: Boolean): Color {
        return if (enabled) this.containerColor else this.disabledContainerColor
    }
}
