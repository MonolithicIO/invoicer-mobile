package io.github.monolithic.features.home.presentation.tabs.welcome.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AttachMoney
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.welcome_icon_customer
import invoicer.multiplatform.features.home.generated.resources.welcome_icon_invoice
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.spacer.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

private const val GridCellsAmount = 3

private enum class WelcomeItems(
    val icon: ImageVector,
    val text: StringResource
) {
    Invoice(
        icon = Icons.Outlined.AttachMoney,
        text = Res.string.welcome_icon_invoice
    ),
    Customer(
        icon = Icons.Outlined.Business,
        text = Res.string.welcome_icon_customer
    ),
}

@Composable
internal fun WelcomeActions(
    onInvoiceClick: () -> Unit,
    onCustomerClick: () -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GridCellsAmount),
        horizontalArrangement = Arrangement.spacedBy(Spacing.small),
        verticalArrangement = Arrangement.spacedBy(Spacing.small)
    ) {
        item(key = WelcomeItems.Invoice.name) {
            ItemCard(
                text = stringResource(WelcomeItems.Invoice.text),
                icon = WelcomeItems.Invoice.icon,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clickable(onClick = onInvoiceClick)
            )
        }
        item(key = WelcomeItems.Customer.name) {
            ItemCard(
                text = stringResource(WelcomeItems.Customer.text),
                icon = WelcomeItems.Customer.icon,
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxWidth()
                    .clickable(onClick = onCustomerClick)
            )
        }
    }
}

@Composable
private fun ItemCard(
    text: String,
    icon: ImageVector,
    modifier: Modifier = Modifier
) {
    Card(modifier = modifier) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.xSmall),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
            )
            VerticalSpacer(SpacerSize.Medium)
            Text(
                text = text,
                style = MaterialTheme.typography.titleSmall
            )
        }
    }
}
