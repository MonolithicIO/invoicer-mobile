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
import androidx.compose.ui.text.style.TextOverflow
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_details_activity_quantity_label
import io.github.alaksion.invoicer.foundation.designSystem.components.ListItem
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

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
                overflow = TextOverflow.Ellipsis
            )
        },
        trailingContent = {
            Text(
                text = content,
                style = MaterialTheme.typography.bodyMedium,
                overflow = TextOverflow.Ellipsis
            )
        }
    )
}

@Composable
internal fun InvoiceDetailsActivityItem(
    name: String,
    quantity: String,
    unitPrice: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        content = {
            Column {
                Text(text = name)
                Text(stringResource(Res.string.invoice_details_activity_quantity_label, quantity))
            }
        },
        trailingContent = {
            Text(
                text = unitPrice,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )
        }
    )
}
