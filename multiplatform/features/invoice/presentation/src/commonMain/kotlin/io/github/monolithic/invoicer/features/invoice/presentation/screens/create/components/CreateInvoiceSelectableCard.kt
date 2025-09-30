package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

private val BorderWidth = 2.dp

@Composable
internal fun CreateInvoiceSelectableCard(
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onSelect: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    val colors = InkTheme.colorScheme

    val borderColor by animateColorAsState(
        targetValue = if (isSelected) colors.primary
        else colors.borderStroke
    )

    InkCard(
        modifier = modifier,
        containerColor = InkTheme.colorScheme.background,
        onClick = onSelect,
        border = BorderStroke(width = BorderWidth, color = borderColor),
        contentPadding = PaddingValues(InkTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
        ) {
            content()
        }
    }
}