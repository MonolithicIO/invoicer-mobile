package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkSnackBar(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: Painter?,
    shape: Shape = InkTheme.shape.small
) {
    Surface(
        color = InkTheme.colorScheme.onBackground,
        shape = shape,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(InkTheme.spacing.small),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            leadingIcon?.let {
                InkIcon(
                    painter = it,
                    contentDescription = null,
                    tint = InkTheme.colorScheme.background,
                    modifier = Modifier.size(IconSize)
                )
            }
            InkText(
                text = text,
                style = InkTextStyle.BodyLarge,
                weight = FontWeight.Medium,
                color = InkTheme.colorScheme.background,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

private val IconSize = 32.dp
