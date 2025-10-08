package io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import invoicer.multiplatform.features.invoice.presentation.generated.resources.Res
import invoicer.multiplatform.features.invoice.presentation.generated.resources.invoice_list_item_id
import invoicer.multiplatform.foundation.design_system.generated.resources.DsResources
import invoicer.multiplatform.foundation.design_system.generated.resources.ic_chevron_right
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.icon.InkIcon
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.RoundTextAbbreviation
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceListItem(
    invoiceNumber: String,
    customerName: String,
    timeStamp: String,
    amount: String,
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
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.xSmall2)
                ) {
                    InkText(
                        text = customerName,
                        style = InkTextStyle.Heading6,
                        weight = FontWeight.SemiBold
                    )
                    InkText(
                        text = timeStamp,
                        style = InkTextStyle.BodyMedium,
                        color = InkTheme.colorScheme.onSurfaceVariant
                    )
                    InkText(
                        text = stringResource(Res.string.invoice_list_item_id, invoiceNumber),
                        style = InkTextStyle.BodyMedium,
                        color = InkTheme.colorScheme.onBackgroundVariant,
                        overflow = TextOverflow.Ellipsis
                    )
                }
                InkText(
                    text = amount,
                    style = InkTextStyle.Heading5,
                    weight = FontWeight.Black,
                    color = InkTheme.colorScheme.primaryVariant
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
