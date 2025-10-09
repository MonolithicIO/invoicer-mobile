package io.github.monolithic.invoicer.features.customer.presentation.screens.list.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import invoicer.multiplatform.features.customer.presentation.generated.resources.Res
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_empty_description
import invoicer.multiplatform.features.customer.presentation.generated.resources.customer_list_empty_title
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chevron_right
import io.github.monolithic.invoicer.features.customer.domain.model.CustomerListItemModel
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.RoundTextAbbreviation
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.screenstate.EmptyState
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.painterResource
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
                CustomerListCard(
                    modifier = Modifier.fillMaxWidth(),
                    customerName = it.name,
                    onClick = {}
                )
            }
        }
    }
}

@Composable
internal fun CustomerListCard(
    customerName: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    InkCard(
        containerColor = InkTheme.colorScheme.surfaceLight,
        onClick = onClick,
        contentPadding = PaddingValues(InkTheme.spacing.small),
        modifier = modifier,
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
            ) {
                RoundTextAbbreviation(
                    text = customerName
                )

                InkText(
                    modifier = Modifier.weight(1f),
                    text = customerName,
                    style = InkTextStyle.Heading5,
                    weight = FontWeight.SemiBold
                )

                InkIcon(
                    painter = painterResource(DsResources.drawable.ic_chevron_right),
                    contentDescription = null,
                    tint = InkTheme.colorScheme.onBackground
                )
            }
        }
    }
}
