package io.github.monolithic.invoicer.features.company.presentation.screens.updatepayaccount.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.runtime.Composable
import invoicer.features.company.presentation.generated.resources.Res
import invoicer.features.company.presentation.generated.resources.delete_pay_account_cancel
import invoicer.features.company.presentation.generated.resources.delete_pay_account_confirm
import invoicer.features.company.presentation.generated.resources.delete_pay_account_description
import invoicer.features.company.presentation.generated.resources.delete_pay_account_title
import io.github.monolithic.invoicer.foundation.designSystem.legacy.components.dialog.DefaultInvoicerDialog
import org.jetbrains.compose.resources.stringResource

@Composable
internal fun DeletePayAccountModal(
    onDismissRequest: () -> Unit,
    onConfirm: () -> Unit,
    isVisible: Boolean,
) {
    if (isVisible) {
        DefaultInvoicerDialog(
            onDismiss = onDismissRequest,
            icon = Icons.Outlined.DeleteOutline,
            title = stringResource(Res.string.delete_pay_account_title),
            description = stringResource(Res.string.delete_pay_account_description),
            confirmButtonText = stringResource(Res.string.delete_pay_account_confirm),
            confirmButtonClick = onConfirm,
            cancelButtonText = stringResource(Res.string.delete_pay_account_cancel)
        )
    }
}
