package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.toggle

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkToggle(
    checked: Boolean,
    onClick: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val colors = InkToggleDefaults.colors

    val handleOffset by animateDpAsState(
        targetValue = if (checked) InkToggleDefaults.width -
                InkToggleDefaults.handleSize -
                (InkToggleDefaults.containerPadding * 2)
        else 0.dp
    )

    val backgroundColor by animateColorAsState(
        targetValue = when {
            !enabled -> colors.disabledBackgroundColor
            checked -> colors.checkedBackgroundColor
            else -> colors.uncheckedBackgroundColor
        }
    )

    Box(
        modifier = modifier
            .size(
                width = InkToggleDefaults.width,
                height = InkToggleDefaults.height
            )
            .clip(InkTheme.shape.pill)
            .toggleable(
                value = checked,
                interactionSource = MutableInteractionSource(),
                indication = null,
                enabled = enabled,
                role = Role.Switch,
                onValueChange = onClick,
            )
            .background(backgroundColor)
            .padding(InkToggleDefaults.containerPadding)
            .offset(handleOffset),
        contentAlignment = Alignment.CenterStart
    ) {
        Box(
            modifier = Modifier
                .size(InkToggleDefaults.handleSize)
                .clip(CircleShape)
                .background(colors.handleColor(enabled))
        )
    }
}

internal data class InkToggleColors(
    val handleColor: Color,
    val checkedBackgroundColor: Color,
    val uncheckedBackgroundColor: Color,
    val disabledHandleColor: Color,
    val disabledBackgroundColor: Color
) {
    fun handleColor(enabled: Boolean): Color = if (enabled) handleColor else disabledHandleColor
}

internal object InkToggleDefaults {

    val height = 28.dp
    val width = 44.dp
    val handleSize = 20.dp
    val containerPadding = 3.dp

    val colors: InkToggleColors
        @Composable
        get() = InkToggleColors(
            handleColor = InkTheme.colorScheme.white,
            checkedBackgroundColor = InkTheme.colorScheme.primary,
            uncheckedBackgroundColor = InkTheme.colorScheme.surfaceDark,
            disabledHandleColor = InkTheme.colorScheme.onDisabled,
            disabledBackgroundColor = InkTheme.colorScheme.disabled,
        )
}
