package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_item_quantity
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_item_total_paid
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_item_unit_price
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_trash_can
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.CreateInvoiceActivityUiModel
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.Spacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIconButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.basic.InkIconButtonDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.utils.money.moneyFormat
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceActivityCard(
    item: CreateInvoiceActivityUiModel,
    onDeleteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    InkCard(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        containerColor = InkTheme.colorScheme.surfaceLight,
        contentPadding = PaddingValues(InkTheme.spacing.medium),
        border = BorderStroke(
            width = 2.dp,
            color = InkTheme.colorScheme.borderStroke
        )
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
            ) {
                InkText(
                    text = item.description,
                    style = InkTextStyle.Heading5,
                    weight = FontWeight.SemiBold,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                InkHorizontalDivider(color = InkTheme.colorScheme.onSurface)
                ActivityItemRow(
                    label = stringResource(Res.string.invoice_create_activity_item_quantity),
                    value = item.quantity.toString()
                )
                InkHorizontalDivider(color = InkTheme.colorScheme.onSurface)
                ActivityItemRow(
                    label = stringResource(Res.string.invoice_create_activity_item_unit_price),
                    value = item.unitPrice.moneyFormat()
                )
                InkHorizontalDivider(color = InkTheme.colorScheme.onSurface)
                InvoiceActivityFooter(totalPrice = item.totalPrice.moneyFormat())
            }
            InkIconButton(
                onClick = onDeleteClick,
                icon = painterResource(DsResources.drawable.ic_trash_can),
                colors = InkIconButtonDefaults.colors.copy(
                    iconColor = InkTheme.colorScheme.error
                )
            )
        }
    }
}


@Composable
private fun InvoiceActivityFooter(
    totalPrice: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        InkText(
            text = stringResource(Res.string.invoice_create_activity_item_total_paid),
            style = InkTextStyle.Heading5,
            weight = FontWeight.SemiBold,
            modifier = Modifier.weight(1f)
        )
        InkText(
            text = totalPrice,
            style = InkTextStyle.Heading5,
            weight = FontWeight.Black,
            color = InkTheme.colorScheme.primaryVariant
        )
    }
}

@Composable
private fun ActivityItemRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        InkText(
            text = label,
            weight = FontWeight.SemiBold,
            style = InkTextStyle.Heading6
        )
        Spacer(1f)
        InkText(
            text = value,
            weight = FontWeight.Bold,
            style = InkTextStyle.Heading6
        )
    }
}