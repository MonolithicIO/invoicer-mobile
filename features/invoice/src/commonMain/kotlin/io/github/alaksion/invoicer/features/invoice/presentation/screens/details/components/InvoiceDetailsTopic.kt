package io.github.alaksion.invoicer.features.invoice.presentation.screens.details.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.alaksion.invoicer.foundation.designSystem.components.ListItem
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing

@Composable
internal fun InvoiceDetailsTopic(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier.padding(Spacing.small),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
            content()
        }
    }
}

@Composable
internal fun InvoiceDetailsTopicItem(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        content = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
            )
        },
        trailingContent = {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    )
}