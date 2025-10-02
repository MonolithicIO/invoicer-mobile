package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.components

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
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_item_quantity
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_create_activity_item_total_paid
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.CreateInvoiceActivityUiModel
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.utils.money.moneyFormat
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceActivityCard(
    item: CreateInvoiceActivityUiModel,
    modifier: Modifier = Modifier
) {
    InkCard(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        containerColor = InkTheme.colorScheme.surfaceLight,
        contentPadding = PaddingValues(InkTheme.spacing.medium)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            InvoiceActivityHeader(
                modifier = Modifier.fillMaxWidth(),
                description = item.description,
                quantity = item.quantity.toString(),
                unitPrice = item.unitPrice.moneyFormat(),
            )
            InkHorizontalDivider(
                color = InkTheme.colorScheme.onSurface
            )
            InvoiceActivityFooter(
                totalPrice = item.totalPrice.moneyFormat()
            )
        }

    }
}

@Composable
private fun InvoiceActivityHeader(
    description: String,
    quantity: String,
    unitPrice: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall3)
        ) {
            InkText(
                text = description,
                style = InkTextStyle.Heading5,
                weight = FontWeight.SemiBold,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
            InkText(
                text = stringResource(
                    Res.string.invoice_create_activity_item_quantity,
                    quantity
                ),
                color = InkTheme.colorScheme.onBackgroundVariant,
                style = InkTextStyle.BodyLarge,
            )
        }
        InkText(
            text = unitPrice,
            style = InkTextStyle.Heading5,
            weight = FontWeight.Bold,
        )
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