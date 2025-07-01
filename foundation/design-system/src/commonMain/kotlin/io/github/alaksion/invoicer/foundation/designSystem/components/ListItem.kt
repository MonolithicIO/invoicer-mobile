package io.github.alaksion.invoicer.foundation.designSystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing

@Composable
fun ListItem(
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    content: @Composable ColumnScope.() -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        leadingContent?.invoke()
        Column(modifier = Modifier.weight(1f)) {
            content()
        }
        trailingContent?.invoke()
    }
}
