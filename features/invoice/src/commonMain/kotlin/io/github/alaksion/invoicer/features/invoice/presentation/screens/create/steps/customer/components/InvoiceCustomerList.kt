package io.github.alaksion.invoicer.features.invoice.presentation.screens.create.steps.customer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_customer_empty_state_description
import invoicer.features.invoice.generated.resources.invoice_customer_empty_state_title
import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListItemModel
import io.github.alaksion.invoicer.foundation.designSystem.components.card.SelectableCard
import io.github.alaksion.invoicer.foundation.designSystem.components.screenstate.EmptyState
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceCustomerList(
    items: ImmutableList<CustomerListItemModel>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    if (items.isEmpty()) {
        EmptyState(
            title = stringResource(Res.string.invoice_customer_empty_state_title),
            description = stringResource(Res.string.invoice_customer_empty_state_description),
            modifier = modifier,
        )
    } else {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            items(
                items = items,
                key = { it.id }
            ) { customer ->
                SelectableCard(
                    isSelected = customer.id == selectedId,
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { onSelect(customer.id) },
                    content = {
                        Text(
                            text = customer.name,
                        )
                    },
                )
            }
        }
    }
}