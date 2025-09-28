package io.github.monolithic.features.home.presentation.screens.home.tabs.account.components

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.home.generated.resources.Res
import invoicer.multiplatform.features.home.generated.resources.home_settings_sign_out_confirmation
import invoicer.multiplatform.features.home.generated.resources.home_settings_sign_out_negative
import invoicer.multiplatform.features.home.generated.resources.home_settings_sign_out_positive
import invoicer.multiplatform.features.home.generated.resources.home_settings_sing_out_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.InkDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import org.jetbrains.compose.resources.stringResource


@Composable
internal fun SignOutDialog(
    isVisible: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit,
) {
    if (isVisible) {
        InkDialog(
            onDismissRequest = onDismiss,
            title = stringResource(Res.string.home_settings_sing_out_title),
            description = stringResource(Res.string.home_settings_sign_out_confirmation),
            primaryAction = InkDialogAction(
                title = stringResource(Res.string.home_settings_sign_out_positive),
                onClick = onConfirm
            ),
            secondaryAction = InkDialogAction(
                title = stringResource(Res.string.home_settings_sign_out_negative),
                onClick = onDismiss
            ),
        )
    }
}
