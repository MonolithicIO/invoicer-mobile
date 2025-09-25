package io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.welcome_latest_invoice_empty_action
import invoicer.multiplatform.features.home.generated.resources.welcome_latest_invoice_empty_subtitle
import invoicer.multiplatform.features.home.generated.resources.welcome_latest_invoice_empty_title
import invoicer.multiplatform.features.home.generated.resources.welcome_latest_invoice_title
import invoicer.multiplatform.features.home.generated.resources.welcome_latest_invoice_view_all
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkTextButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.props.InkButtonSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.divider.InkHorizontalDivider
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.CompanyNameIcon
import kotlinx.collections.immutable.ImmutableList
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun LatestInvoicesCard(
    items: ImmutableList<LatestInvoiceUiModel>,
    modifier: Modifier = Modifier,
    onViewAllClick: () -> Unit,
    onCreateInvoiceClick: () -> Unit,
) {
    InkCard(
        modifier = modifier,
        containerColor = InkTheme.colorScheme.background,
        contentPadding = PaddingValues(InkTheme.spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium)
        ) {
            InkText(
                text = stringResource(Res.string.welcome_latest_invoice_title),
                style = InkTextStyle.Heading5,
                weight = FontWeight.Bold,
                modifier = Modifier.weight(1f)
            )
            if (items.isNotEmpty()) {
                InkTextButton(
                    text = stringResource(Res.string.welcome_latest_invoice_view_all),
                    onClick = onViewAllClick
                )
            }
        }
        VerticalSpacer(SpacerSize.Medium)

        if (items.isNotEmpty()) {
            LatestInvoiceList(
                items = items,
                modifier = Modifier.fillMaxWidth()
            )
        } else {
            LatestInvoiceEmpty(
                modifier = Modifier.fillMaxWidth(),
                onClick = onCreateInvoiceClick
            )
        }
    }
}

@Composable
private fun LatestInvoiceList(
    items: ImmutableList<LatestInvoiceUiModel>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        items.forEachIndexed { index, item ->
            LastestInvoiceItem(
                item = item,
                modifier = Modifier.fillMaxWidth()
            )
            if (index != items.lastIndex) {
                InkHorizontalDivider()
            }
        }
    }
}

@Composable
private fun LastestInvoiceItem(
    item: LatestInvoiceUiModel,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        CompanyNameIcon(name = item.companyName)
        Column(modifier = Modifier.weight(1f)) {
            InkText(
                text = item.companyName,
                style = InkTextStyle.Heading6,
                weight = FontWeight.SemiBold
            )
            InkText(
                text = item.timeStamp,
                style = InkTextStyle.BodyMedium,
                color = InkTheme.colorScheme.onBackgroundVariant
            )
        }
        InkText(
            text = item.amount,
            style = InkTextStyle.Heading6,
            weight = FontWeight.SemiBold,
            color = InkTheme.colorScheme.onBackground
        )
    }
}

@Composable
private fun LatestInvoiceEmpty(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.small)
    ) {
        InkText(
            text = stringResource(Res.string.welcome_latest_invoice_empty_title),
            style = InkTextStyle.Heading5,
            weight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        InkText(
            text = stringResource(Res.string.welcome_latest_invoice_empty_subtitle),
            style = InkTextStyle.BodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
        InkPrimaryButton(
            text = stringResource(Res.string.welcome_latest_invoice_empty_action),
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            size = InkButtonSize.Small
        )
    }
}

internal data class LatestInvoiceUiModel(
    val companyName: String,
    val amount: String,
    val timeStamp: String,
)
