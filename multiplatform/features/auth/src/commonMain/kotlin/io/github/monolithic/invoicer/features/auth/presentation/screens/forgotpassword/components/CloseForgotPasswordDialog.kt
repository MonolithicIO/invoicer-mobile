package io.github.monolithic.invoicer.features.auth.presentation.screens.forgotpassword.components

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.auth.generated.resources.Res
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_dialog_cancel
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_dialog_confirm
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_dialog_description
import invoicer.multiplatform.features.auth.generated.resources.forgot_password_otp_dialog_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.InkDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun CloseForgotPasswordDialog(
    onCloseFlow: () -> Unit,
    onDismissDialog: () -> Unit
) {
    InkDialog(
        onDismissRequest = onDismissDialog,
        title = stringResource(Res.string.forgot_password_otp_dialog_title),
        description = stringResource(Res.string.forgot_password_otp_dialog_description),
        primaryAction = InkDialogAction(
            title = stringResource(Res.string.forgot_password_otp_dialog_confirm),
            onClick = onCloseFlow
        ),
        secondaryAction = InkDialogAction(
            title = stringResource(Res.string.forgot_password_otp_dialog_cancel),
            onClick = onDismissDialog
        )
    )
}
