package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import invoicer.foundation.design_system.generated.resources.Res
import invoicer.foundation.design_system.generated.resources.danger_circle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun InkInputErrorText(
    supportText: String,
    modifier: Modifier = Modifier
) {
    val colors = InkInputDefaults.colors
    BasicSupportText(
        backgroundColor = colors.errorSupportTextBackground,
        text = supportText,
        shape = InkTheme.shape.small,
        textColor = colors.errorText,
        modifier = modifier
    )
}

@Composable
private fun BasicSupportText(
    backgroundColor: Color,
    shape: Shape,
    textColor: Color,
    text: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier
            .background(backgroundColor, shape)
            .padding(InkTheme.spacing.xSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall)
    ) {
        Icon(
            painter = painterResource(Res.drawable.danger_circle),
            contentDescription = null,
            tint = InkTheme.colorScheme.error
        )
        InkText(
            modifier = Modifier.fillMaxWidth(),
            text = text,
            style = InkTextStyle.BodyMedium,
            color = textColor,
            maxLines = 1
        )
    }
}
