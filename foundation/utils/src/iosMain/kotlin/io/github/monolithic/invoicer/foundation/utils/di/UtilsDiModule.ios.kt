package io.github.monolithic.invoicer.foundation.utils.di

import io.github.monolithic.invoicer.foundation.utils.logger.InvoicerLogger
import io.github.monolithic.invoicer.foundation.utils.logger.IosInvoicerLogger
import org.koin.core.module.Module
import org.koin.dsl.module

actual val utilPlatformModule: Module = module {
    factory<InvoicerLogger> {
        IosInvoicerLogger()
    }
}
