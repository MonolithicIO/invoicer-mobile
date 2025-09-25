package io.github.monolithic.invoicer.features.invoice.services.di

import io.github.monolithic.invoicer.features.invoice.services.domain.repository.InvoiceRepository
import io.github.monolithic.invoicer.features.invoice.services.data.datasource.InvoiceDataSource
import io.github.monolithic.invoicer.features.invoice.services.data.datasource.InvoiceDataSourceImpl
import io.github.monolithic.invoicer.features.invoice.services.data.repository.InvoiceRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.koin.dsl.module

val invoiceServicesDiModule = module {
    factory<InvoiceDataSource> {
        InvoiceDataSourceImpl(
            httpClient = get(),
            dispatcher = Dispatchers.IO
        )
    }
    factory<InvoiceRepository> { InvoiceRepositoryImpl(dataSource = get()) }
}