package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

internal object InkInputDefaults {

    val MinHeight = 64.dp
    val MinWidth = 280.dp
    const val DEFAULT_ERROR_MESSAGE = "Error"
    const val TextFieldAnimationDuration = 200

    val shape: Shape
        @Composable
        get() {
            return InkTheme.shape.small
        }

    val colors: InkInputColors
        @Composable
        get() {
            return InkInputColors(
                textColor = InkTheme.colorScheme.onSurfaceVariant,
                cursorColor = InkTheme.colorScheme.onSurfaceVariant,
                indicator = InkTheme.colorScheme.surfaceLight,
                disabledIndicator = InkTheme.colorScheme.disabled,
                disabledBackground = InkTheme.colorScheme.disabled,
                disabledText = InkTheme.colorScheme.onDisabled,
                disabledLabel = InkTheme.colorScheme.onDisabled,
                errorText = InkTheme.colorScheme.error,
                errorIndicator = InkTheme.colorScheme.error,
                errorLabel = InkTheme.colorScheme.error,
                errorCursor = InkTheme.colorScheme.error,
                errorPlaceholder = InkTheme.colorScheme.error,
                placeholderColor = InkTheme.colorScheme.onDisabled,
                labelColor = InkTheme.colorScheme.onSurfaceVariant,
                supportTextColor = InkTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f),
                background = InkTheme.colorScheme.surfaceLight,
                focusedIndicator = InkTheme.colorScheme.primary
            )
        }

    @Composable
    fun backgroundColor(isEnabled: Boolean): State<Color> {
        val colors = colors

        val newColor = when {
            !isEnabled -> colors.disabledBackground
            else -> colors.background
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun borderColor(isError: Boolean, isFocused: Boolean, isEnabled: Boolean): State<Color> {
        val colors = colors
        val newColor = when {
            !isEnabled -> colors.disabledIndicator
            isError -> colors.errorIndicator
            isFocused -> colors.focusedIndicator
            else -> colors.indicator
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun labelColor(isError: Boolean, isEnabled: Boolean): State<Color> {
        val colors = colors
        val newColor = when {
            !isEnabled -> colors.disabledLabel
            isError -> colors.errorLabel
            else -> colors.labelColor
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun placeholderColor(isError: Boolean): Color {
        return if (isError) colors.errorPlaceholder else colors.placeholderColor
    }

    @Composable
    fun supportTextColor(isError: Boolean, isEnabled: Boolean): State<Color> {
        val color = when {
            !isEnabled -> colors.disabledText
            isError -> colors.errorText
            else -> colors.supportTextColor
        }
        return animateColorAsState(
            targetValue = color,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }
}
