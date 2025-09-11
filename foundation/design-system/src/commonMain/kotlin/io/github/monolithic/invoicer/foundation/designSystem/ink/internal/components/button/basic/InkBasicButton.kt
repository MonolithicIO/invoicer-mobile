package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.local.LocalInkShape
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme


@Composable
internal fun InkBasicButton(
    onClick: () -> Unit,
    enabled: Boolean = true,
    size: InkButtonSize = InkButtonSize.Regular,
    backgroundColor: InkColor,
    contentColor: InkColor,
    borderStroke: BorderStroke? = null,
    modifier: Modifier = Modifier,
    content: @Composable RowScope.() -> Unit,
) {
    Surface(
        onClick = onClick,
        modifier = modifier,
        shape = LocalInkShape.current.small,
        color = backgroundColor.value,
        contentColor = contentColor.value,
        border = borderStroke,
        enabled = enabled
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = size.height)
                .padding(InkTheme.spacing.medium),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            content()
        }
    }
}
