package io.github.alaksion.invoicer.foundation.designSystem.components.card

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SelectableCard(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    leadingContent: (@Composable () -> Unit)? = null,
    content: @Composable () -> Unit,
) {
    val colors = MaterialTheme.colorScheme

    val borderTint = remember(isSelected) {
        if (isSelected) {
            colors.primary
        } else {
            colors.surfaceVariant
        }
    }

    ListCard(
        modifier = modifier
            .border(
                width = 2.dp,
                color = borderTint,
                shape = MaterialTheme.shapes.medium
            )
            .clickable(onClick = onClick),
        content = content,
        leadingContent = leadingContent,
        trailingContent = {
            RadioButton(
                selected = isSelected,
                onClick = null
            )
        }
    )
}
