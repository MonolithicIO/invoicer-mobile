package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun ColumnScope.Spacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun RowScope.Spacer(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun VerticalSpacer(
    height: SpacerSize
) {
    Spacer(modifier = Modifier.height(height.value))
}

@Composable
fun HorizontalSpacer(
    width: SpacerSize
) {
    Spacer(modifier = Modifier.width(width.value))
}

enum class SpacerSize {
    XSmall2,
    XSmall,
    Small,
    Medium,
    Large,
    XLarge,
    XLarge3;

    internal val value: Dp
        @Composable
        get() {
            val spacing = InkTheme.spacing

            return when (this) {
                XSmall2 -> spacing.xSmall2
                XSmall -> spacing.xSmall
                Small -> spacing.small
                Medium -> spacing.medium
                Large -> spacing.large
                XLarge -> spacing.xLarge
                XLarge3 -> spacing.xLarge3
            }
        }
}
