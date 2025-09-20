package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.snackbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
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
        color = InkTheme.colorScheme.background,
        shape = shape
    ) {
        Row(
            modifier = modifier,
            verticalAlignment = Alignment.CenterVertically
        ) {
            leadingIcon?.let {
                InkIcon(
                    painter = it,
                    contentDescription = null,
                    tint = InkTheme.colorScheme.onBackground,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
            InkText(
                text = text,
                style = InkTextStyle.BodyLarge,
                weight = FontWeight.Medium,
                color = InkTheme.colorScheme.onBackground,
                modifier = Modifier.weight(1f)
            )
        }
    }
}
