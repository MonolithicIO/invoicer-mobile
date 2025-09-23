package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme


@Composable
internal fun InkBasicButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    size: InkButtonSize = InkButtonSize.Regular,
    backgroundColor: Color,
    contentColor: Color,
    borderStroke: BorderStroke? = null,
    modifier: Modifier = Modifier,
    shape: Shape = LocalInkShape.current.small,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = shape,
        color = backgroundColor,
        contentColor = contentColor,
        border = borderStroke,
        enabled = enabled
    ) {
        Row(
            modifier = Modifier
                .defaultMinSize(minHeight = size.height)
                .padding(InkTheme.spacing.medium),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
