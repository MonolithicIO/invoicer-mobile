package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ListItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.features.qrcode_session.generated.resources.Res
import invoicer.features.qrcode_session.generated.resources.qr_code_details_agent
import invoicer.features.qrcode_session.generated.resources.qr_code_details_authorize
import invoicer.features.qrcode_session.generated.resources.qr_code_details_expiration
import invoicer.features.qrcode_session.generated.resources.qr_code_details_ip
import invoicer.features.qrcode_session.generated.resources.qr_code_details_requested_at
import io.github.monolithic.invoicer.foundation.designSystem.legacy.tokens.Spacing
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CodeDetails(
    modifier: Modifier = Modifier,
    qrCodeAgent: String,
    qrCodeIp: String,
    qrCodeExpiration: String,
    qrCodeEmission: String,
    onAuthorize: () -> Unit,
) {
    Card(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Spacing.medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(Spacing.small)
        ) {
            ListItem(
                headlineContent = { Text(text = qrCodeAgent) },
                overlineContent = {
                    Text(stringResource(Res.string.qr_code_details_agent))
                }
            )
            ListItem(
                headlineContent = { Text(text = qrCodeIp) },
                overlineContent = {
                    Text(stringResource(Res.string.qr_code_details_ip))
                }
            )
            ListItem(
                headlineContent = { Text(text = qrCodeExpiration) },
                overlineContent = {
                    Text(stringResource(Res.string.qr_code_details_expiration))
                }
            )
            ListItem(
                headlineContent = { Text(text = qrCodeEmission) },
                overlineContent = {
                    Text(stringResource(Res.string.qr_code_details_requested_at))
                }
            )
            Button(
                onClick = onAuthorize,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(Res.string.qr_code_details_authorize))
            }
        }
    }
}
