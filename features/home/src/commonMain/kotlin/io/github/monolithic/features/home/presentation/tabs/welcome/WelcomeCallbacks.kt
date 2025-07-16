package io.github.monolithic.features.home.presentation.tabs.welcome

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

internal data class WelcomeCallbacks(
    val onInvoiceClick: () -> Unit,
    val onCustomerClick: () -> Unit,
    val onChangeCompanyClick: () -> Unit,
)

@Composable
internal fun rememberWelcomeCallbacks(
    onInvoiceClick: () -> Unit,
    onCustomerClick: () -> Unit,
    onChangeCompanyClick: () -> Unit,
): WelcomeCallbacks {
    return remember {
        WelcomeCallbacks(
            onInvoiceClick = onInvoiceClick,
            onCustomerClick = onCustomerClick,
            onChangeCompanyClick = onChangeCompanyClick
        )
    }
}
