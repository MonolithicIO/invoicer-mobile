package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic

import androidx.compose.foundation.Indication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.minimumInteractiveComponentSize
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.props.InkIconButtonColors
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
internal fun BasicInkIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource? = null,
    content: @Composable () -> Unit,
) {
    Box(
        modifier =
            modifier
                .minimumInteractiveComponentSize()
                .size(BasicInkIconButtonDefaults.StateLayerSize)
                .clip(BasicInkIconButtonDefaults.shape)
                .background(color = BasicInkIconButtonDefaults.backgroundColor(enabled))
                .clickable(
                    onClick = onClick,
                    enabled = enabled,
                    role = Role.Button,
                    interactionSource = interactionSource,
                    indication =
                        rippleOrFallbackImplementation(
                            bounded = false,
                            radius = BasicInkIconButtonDefaults.StateLayerSize / 2
                        )
                ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}

@Composable
private fun rippleOrFallbackImplementation(
    bounded: Boolean = true,
    radius: Dp = Dp.Unspecified,
    color: Color = Color.Unspecified
): Indication {
    return ripple(bounded, radius, color)
}

internal object BasicInkIconButtonDefaults {

    val shape = CircleShape
    val StateLayerSize = 40.0.dp
    val colors: InkIconButtonColors
        @Composable
        get() {
            val theme = InkTheme.colorScheme
            return remember {
                InkIconButtonColors(
                    containerColor = theme.surfaceVariant,
                    disabledContainerColor = theme.disabled,
                    disabledContentColor = theme.onDisabled
                )
            }
        }

    @Composable
    fun backgroundColor(enabled: Boolean): Color {
        val colors = colors
        return if (enabled) colors.containerColor else colors.disabledContainerColor
    }
}
