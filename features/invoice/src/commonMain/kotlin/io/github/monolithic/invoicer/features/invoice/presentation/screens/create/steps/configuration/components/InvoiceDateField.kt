package io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.configuration.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CalendarMonth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import invoicer.features.invoice.generated.resources.Res
import invoicer.features.invoice.generated.resources.invoice_create_dates_change_cta
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.ListItem
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.buttons.PrimaryButton
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun InvoiceDateField(
    label: String,
    content: String,
    errorMessage: String? = null,
    onChangeClick: () -> Unit
) {
    Column {
        ListItem(
            leadingContent = {
                Icon(
                    imageVector = Icons.Outlined.CalendarMonth,
                    contentDescription = null
                )
            },
            trailingContent = {
                PrimaryButton(
                    label = stringResource(Res.string.invoice_create_dates_change_cta),
                    onClick = onChangeClick
                )
            },
            content = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = content,
                )
            }
        )
        Text(
            text = errorMessage.orEmpty(),
            color = MaterialTheme.colorScheme.error
        )
    }
}
