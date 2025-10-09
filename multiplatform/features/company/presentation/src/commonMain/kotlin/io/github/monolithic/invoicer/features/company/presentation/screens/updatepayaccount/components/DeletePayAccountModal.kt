package io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.components

import androidx.compose.runtime.Composable
import invoicer.multiplatform.features.company.presentation.generated.resources.Res
import invoicer.multiplatform.features.company.presentation.generated.resources.delete_pay_account_cancel
import invoicer.multiplatform.features.company.presentation.generated.resources.delete_pay_account_confirm
import invoicer.multiplatform.features.company.presentation.generated.resources.delete_pay_account_description
import invoicer.multiplatform.features.company.presentation.generated.resources.delete_pay_account_title
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.InkDialog
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.dialog.props.InkDialogAction
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DeletePayAccountModal(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    isVisible: Boolean,
) {
    if (isVisible) {
        InkDialog(
            onDismissRequest = onDismissRequest,
            title = stringResource(Res.string.delete_pay_account_title),
            description = stringResource(Res.string.delete_pay_account_description),
            primaryAction = InkDialogAction(
                title = stringResource(Res.string.delete_pay_account_confirm),
                onClick = onConfirm
            ),
            secondaryAction = InkDialogAction(
                title = stringResource(Res.string.delete_pay_account_cancel),
                onClick = onDismissRequest
            ),
        )
    }
}
