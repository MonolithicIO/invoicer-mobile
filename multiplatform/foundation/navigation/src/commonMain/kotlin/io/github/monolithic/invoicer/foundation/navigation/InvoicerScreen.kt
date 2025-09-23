package io.github.monolithic.invoicer.foundation.navigation

import cafe.adriel.voyager.core.registry.ScreenProvider
import io.github.monolithic.invoicer.foundation.navigation.args.SelectCompanyIntent

sealed interface InvoicerScreen : ScreenProvider {
    sealed interface Auth : InvoicerScreen {
        data object AuthMenu : Auth
        data object Startup : Auth
    }

    data object Home : InvoicerScreen

    sealed interface Invoices : InvoicerScreen {
        data object List : Invoices
        data object Create : Invoices
    }

    sealed interface Authorization : ScreenProvider {
        data object Home : Authorization
    }

    sealed interface Company : InvoicerScreen {
        data class SelectCompany(
            val intent: SelectCompanyIntent
        ) : Company

        data object Details : Company

        data object CreateCompany : Company
    }

    sealed interface Customer : InvoicerScreen {
        data object Create : Customer
        data object List : Customer
    }
}
