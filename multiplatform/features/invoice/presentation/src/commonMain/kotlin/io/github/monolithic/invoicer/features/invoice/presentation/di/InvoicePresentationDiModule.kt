package io.github.monolithic.invoicer.features.invoice.presentation.di

import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceFormManager
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.InvoiceActivitiesScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.configuration.InvoiceConfigurationScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.InvoiceConfirmationScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.customer.InvoiceCustomerScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListScreenModel
import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module

val invoicePresentationDiModule = module {
    single { CreateInvoiceFormManager() }

    factory<CreateInvoiceForm> {
        get<CreateInvoiceFormManager>().getForm()
    }

    factory {
        InvoiceConfigurationScreenModel(
            invoiceForm = get(),
            clock = get()
        )
    }

    factory {
        InvoiceCustomerScreenModel(
            customerRepository = get(),
            dispatcher = Dispatchers.Default,
            session = get(),
            createInvoiceForm = get(),
            newCustomerEventBus = get()
        )
    }

    factory {
        InvoiceConfirmationScreenModel(
            form = get(),
            session = get(),
            invoiceRepository = get(),
            dispatcher = Dispatchers.Default,
            homeRefreshBus = get()
        )
    }

    factory {
        InvoiceDetailsScreenModel(
            invoiceRepository = get(),
            dispatcher = Dispatchers.Default,
            session = get()
        )
    }

    factory<InvoiceListScreenModel> {
        InvoiceListScreenModel(
            invoiceRepository = get(),
            dispatcher = Dispatchers.Default,
            session = get()
        )
    }


    factory {
        InvoiceActivitiesScreenModel(
            dispatcher = Dispatchers.Default,
            createInvoiceManager = get()
        )
    }
}
