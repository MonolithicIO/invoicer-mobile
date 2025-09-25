package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkCard(
    modifier: Modifier = Modifier,
    shape: Shape = InkTheme.shape.small,
    border: BorderStroke? = null,
    contentPadding: PaddingValues = PaddingValues(),
    elevation: Dp = 0.dp,
    containerColor: Color = InkTheme.colorScheme.surface,
    content: @Composable ColumnScope.() -> Unit,
) {
    Surface(
        modifier = modifier,
        shape = shape,
        color = containerColor,
        contentColor = Color.Unspecified,
        shadowElevation = elevation,
        border = border,
    ) {
        Column(
            modifier = Modifier.padding(contentPadding),
            content = content
        )
    }
}

