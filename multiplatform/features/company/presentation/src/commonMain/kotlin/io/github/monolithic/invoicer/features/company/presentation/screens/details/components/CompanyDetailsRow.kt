package io.github.monolithic.invoicer.features.company.presentation.screens.details.components

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.ListItem

@Composable
internal fun CompanyDetailsRow(
    title: String,
    content: String,
    modifier: Modifier = Modifier
) {
    ListItem(
        modifier = modifier,
        content = {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        },
        trailingContent = {
            Text(
                text = content,
            )
        }
    )
}
