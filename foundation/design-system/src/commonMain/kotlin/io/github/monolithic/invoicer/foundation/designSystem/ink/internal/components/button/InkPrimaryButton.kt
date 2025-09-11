package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkColor
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkPrimaryButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean,
    size: InkButtonSize = InkButtonSize.Regular,
) {
    InkBasicButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && loading.not(),
        size = size,
        backgroundColor = InkPrimaryButtonDefaults.getBackgroundColor(enabled),
        contentColor = InkPrimaryButtonDefaults.getContentColor(enabled),
        borderStroke = null,
    ) {
        if (loading) {
            InkCircularIndicator(
                color = InkTheme.colorScheme.onPrimary,
                modifier = Modifier.size(InkPrimaryButtonDefaults.LoadingSize)
            )
        } else {
            InkText(
                text = text,
                style = InkTextStyle.BodyLarge,
                weight = FontWeight.Bold,
                color = InkPrimaryButtonDefaults.getContentColor(enabled)
            )
        }
    }
}


internal object InkPrimaryButtonDefaults {

    @Composable
    fun getBackgroundColor(isEnabled: Boolean): InkColor {
        return if (isEnabled) {
            InkTheme.colorScheme.primary
        } else {
            InkTheme.colorScheme.disabled
        }
    }

    @Composable
    fun getContentColor(isEnabled: Boolean): InkColor {
        return if (isEnabled) {
            InkTheme.colorScheme.onPrimary
        } else {
            InkTheme.colorScheme.onDisabled
        }
    }

    val LoadingSize = 32.dp
}
