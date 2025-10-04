package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.customer.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_customer_create
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_customer_empty_state_description
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_customer_empty_state_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_user_group
import io.github.monolithic.invoicer.features.customer.domain.model.CustomerListItemModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.components.CreateInvoiceSelectableCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkTextButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.EmptyState
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceCustomerList(
    items: ImmutableList<CustomerListItemModel>,
    selectedId: String?,
    onSelect: (String) -> Unit,
    onNewCustomerClick: () -> Unit,
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
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            items(
                items = items,
                key = { it.id }
            ) { customer ->
                CreateInvoiceSelectableCard(
                    isSelected = customer.id == selectedId,
                    onSelect = { onSelect(customer.id) }
                ) {
                    InkIcon(
                        painter = painterResource(DsResources.drawable.ic_user_group),
                        contentDescription = null,
                        tint = InkTheme.colorScheme.onBackground
                    )
                    InkText(
                        text = customer.name,
                        style = InkTextStyle.Heading6,
                        weight = FontWeight.SemiBold
                    )
                }
            }
            item(key = "new-customer") {
                InkTextButton(
                    text = stringResource(Res.string.invoice_customer_create),
                    onClick = onNewCustomerClick
                )
            }
        }
    }
}
