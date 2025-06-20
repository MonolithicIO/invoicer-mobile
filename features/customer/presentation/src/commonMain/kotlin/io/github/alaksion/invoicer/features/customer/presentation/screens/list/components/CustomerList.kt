package io.github.alaksion.invoicer.features.customer.presentation.screens.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.customer.presentation.generated.resources.Res
import invoicer.features.customer.presentation.generated.resources.customer_list_empty_description
import invoicer.features.customer.presentation.generated.resources.customer_list_empty_title
import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListItemModel
import io.github.alaksion.invoicer.foundation.designSystem.components.screenstate.EmptyState
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CustomerList(
    modifier: Modifier = Modifier,
    items: ImmutableList<CustomerListItemModel>
) {
    if (items.isEmpty()) {
        EmptyState(
            modifier = modifier,
            title = stringResource(Res.string.customer_list_empty_title),
            description = stringResource(Res.string.customer_list_empty_description)
        )
    } else {
        LazyColumn(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            items(
                items = items,
                key = { it.id }
            ) {
                Text(
                    text = it.name
                )
            }
        }
    }
}
