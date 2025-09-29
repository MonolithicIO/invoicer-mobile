package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button

import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCircularIndicator
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.basic.InkBasicButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkPrimaryButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    loading: Boolean = false,
    size: InkButtonSize = InkButtonSize.Regular,
    onClick: () -> Unit,
) {
    InkBasicButton(
        onClick = onClick,
        modifier = modifier,
        enabled = enabled && loading.not(),
        size = size,
        backgroundColor = InkPrimaryButtonDefaults.getBackgroundColor(
            enabled, loading
        ),
        contentColor = InkPrimaryButtonDefaults.getContentColor(
            enabled, loading
        ),
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
                color = InkPrimaryButtonDefaults.getContentColor(
                    enabled, loading
                )
            )
        }
    }
}


internal object InkPrimaryButtonDefaults {

    @Composable
    fun getBackgroundColor(isEnabled: Boolean, isLoading: Boolean): Color {
        return if (isEnabled || isLoading) {
            InkTheme.colorScheme.primary
        } else {
            InkTheme.colorScheme.disabled
        }
    }

    @Composable
    fun getContentColor(isEnabled: Boolean, isLoading: Boolean): Color {
        return if (isEnabled || isLoading) {
            InkTheme.colorScheme.onPrimary
        } else {
            InkTheme.colorScheme.onDisabled
        }
    }

    val LoadingSize = 32.dp
}
