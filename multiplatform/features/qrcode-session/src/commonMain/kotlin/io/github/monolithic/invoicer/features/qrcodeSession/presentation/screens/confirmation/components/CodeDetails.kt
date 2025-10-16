package io.github.monolithic.invoicer.features.qrcodeSession.presentation.screens.confirmation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import invoicer.multiplatform.features.qrcode_session.generated.resources.Res
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_agent
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_authorize
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_expiration
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_ip
import invoicer.multiplatform.features.qrcode_session.generated.resources.qr_code_details_requested_at
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkCard
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme
import io.github.monolithic.invoicer.foundation.designSystem.ink.public.components.LabeledListItem
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
    InkCard(
        modifier = modifier,
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(InkTheme.spacing.medium),
            modifier = Modifier
                .fillMaxWidth()
                .padding(InkTheme.spacing.small)
        ) {
            LabeledListItem(
                label = stringResource(Res.string.qr_code_details_agent),
                value = qrCodeAgent,
            )
            LabeledListItem(
                label = stringResource(Res.string.qr_code_details_ip),
                value = qrCodeIp,
            )

            LabeledListItem(
                label = stringResource(Res.string.qr_code_details_expiration),
                value = qrCodeExpiration,
            )

            LabeledListItem(
                label = stringResource(Res.string.qr_code_details_requested_at),
                value = qrCodeEmission,
            )

            InkPrimaryButton(
                onClick = onAuthorize,
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.qr_code_details_authorize)
            )
        }
    }
}
