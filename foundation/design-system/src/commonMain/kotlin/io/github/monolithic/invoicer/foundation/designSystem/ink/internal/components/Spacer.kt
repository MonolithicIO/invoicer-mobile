package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.specs.InkSpacing

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

enum class SpacerSize(
    internal val value: Dp
) {
    XSmall2(InkSpacing.xSmall2),
    XSmall(InkSpacing.xSmall),
    Small(InkSpacing.small),
    Medium(InkSpacing.medium),
    Large(InkSpacing.large),
    XLarge(InkSpacing.xLarge),
    XLarge3(InkSpacing.xLarge3)
}
