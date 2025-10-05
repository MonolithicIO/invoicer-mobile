package io.github.monolithic.invoicer.features.invoice.presentation.screens.details.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_activity_quantity_label
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_details_activity_unite_price_label
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceDetailsActivityCard(
    description: String,
    unitPrice: String,
    quantity: String,
    totalAmount: String,
    modifier: Modifier = Modifier
) {
    InkCard(
        modifier = modifier,
        contentPadding = PaddingValues(InkTheme.spacing.small),
        border = BorderStroke(width = 1.dp, color = InkTheme.colorScheme.primary),
        containerColor = InkTheme.colorScheme.surfaceLight
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            Column(modifier = Modifier.weight(1f)) {
                InkText(
                    text = description,
                )
                InkText(
                    text = stringResource(
                        resource = Res.string.invoice_details_activity_quantity_label,
                        quantity
                    ),
                )
                InkText(
                    text = stringResource(
                        resource = Res.string.invoice_details_activity_unite_price_label,
                        unitPrice
                    ),
                )
            }
            InkText(
                text = totalAmount,
                color = InkTheme.colorScheme.primaryVariant,
                weight = FontWeight.Black,
                style = InkTextStyle.BodyLarge
            )
        }
    }
}