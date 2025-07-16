package io.github.monolithic.invoicer.foundation.utils.logger

internal class IosInvoicerLogger : InvoicerLogger {

    override fun logDebug(message: String, key: String) = Unit

    override fun logError(message: String, key: String, throwable: Throwable?) = Unit
}
