package io.github.monolithic.invoicer.features.invoice.di

import io.github.monolithic.invoicer.features.invoice.data.datasource.InvoiceDataSource
import io.github.monolithic.invoicer.features.invoice.data.datasource.InvoiceDataSourceImpl
import io.github.monolithic.invoicer.features.invoice.data.repository.InvoiceRepositoryImpl
import io.github.monolithic.invoicer.features.invoice.domain.repository.InvoiceRepository
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceForm
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.CreateInvoiceFormManager
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.InvoiceActivitiesScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.configuration.InvoiceConfigurationScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.confirmation.InvoiceConfirmationScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.customer.InvoiceCustomerScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.details.InvoiceDetailsScreenModel
import io.github.monolithic.invoicer.features.invoice.presentation.screens.invoicelist.state.InvoiceListScreenModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.core.module.Module
import org.koin.dsl.module

val invoiceDiModule = module {
    presentation()
    data()

}

private fun Module.presentation() {
    single { CreateInvoiceFormManager() }

    factory<CreateInvoiceForm> {
        get<CreateInvoiceFormManager>().getForm()
    }

    screenModels()
}

private fun Module.screenModels() {
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
            createInvoiceForm = get()
        )
    }

    factory {
        InvoiceConfirmationScreenModel(
            form = get(),
            session = get(),
            invoiceRepository = get(),
            dispatcher = Dispatchers.Default
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

private fun Module.data() {
    factory<InvoiceDataSource> {
        InvoiceDataSourceImpl(
            httpWrapper = get(),
            dispatcher = Dispatchers.IO
        )
    }
    factory<InvoiceRepository> { InvoiceRepositoryImpl(dataSource = get()) }
}
