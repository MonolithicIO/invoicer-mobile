package io.github.alaksion.features.home.presentation.tabs.welcome.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Business
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import invoicer.features.home.generated.resources.Res
import invoicer.features.home.generated.resources.welcome_change_company
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun WelcomeTopBar(
    companyName: String,
    isChangeCompanyEnabled: Boolean,
    modifier: Modifier = Modifier,
    onChangeClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.primary)
            .padding(Spacing.medium)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Outlined.Business,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onPrimary
            )

            Text(
                text = companyName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.weight(1f),
                overflow = TextOverflow.Ellipsis
            )

            if (isChangeCompanyEnabled) {
                OutlinedButton(
                    onClick = onChangeClick,
                    enabled = true,
                ) {
                    Text(
                        text = stringResource(Res.string.welcome_change_company),
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}