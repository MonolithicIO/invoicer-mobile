package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.Spacer

@Composable
fun InkSocialButton(
    text: String,
    iconPainter: Painter,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
    onClick: () -> Unit
) {
    InkBasicButton(
        modifier = modifier,
        onClick = onClick,
        backgroundColor = InkTheme.colorScheme.surfaceVariant,
        contentColor = InkTheme.colorScheme.onSurfaceVariant,
        borderStroke = BorderStroke(
            width = 1.dp,
            color = InkTheme.colorScheme.borderStroke.value
        ),
    ) {
        Icon(
            painter = iconPainter,
            contentDescription = contentDescription,
            tint = Color.Unspecified,
            modifier = Modifier.size(24.dp)
        )
        Spacer(1f)
        InkText(
            text = text,
            style = InkTextStyle.BodyLarge,
            weight = FontWeight.Bold,
            color = InkTheme.colorScheme.onSurfaceVariant
        )
        Spacer(1f)
    }
}
