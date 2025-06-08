package io.github.alaksion.features.home.presentation.tabs.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class WelcomeCallbacks(
    val onInvoiceClick: () -> Unit,
    val onBeneficiaryClick: () -> Unit,
    val onIntermediaryClick: () -> Unit,
    val onChangeCompanyClick: () -> Unit,
)

@Composable
internal fun rememberWelcomeCallbacks(
    onInvoiceClick: () -> Unit,
    onBeneficiaryClick: () -> Unit,
    onIntermediaryClick: () -> Unit,
    onChangeCompanyClick: () -> Unit,
): WelcomeCallbacks {
    return remember {
        WelcomeCallbacks(
            onInvoiceClick = onInvoiceClick,
            onBeneficiaryClick = onBeneficiaryClick,
            onIntermediaryClick = onIntermediaryClick,
            onChangeCompanyClick = onChangeCompanyClick
        )
    }
}
