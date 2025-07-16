package io.github.monolithic.invoicer.features.invoice.presentation.screens.create

import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.CreateInvoiceActivityUiModel
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin

internal class CreateInvoiceForm {

    var invoiceNumber: String = ""
    var customerId: String = ""
    var customerName: String = ""
    var issueDate: Long = 0
    var dueDate: Long = 0

    var activities = mutableListOf<CreateInvoiceActivityUiModel>()
}

internal class CreateInvoiceFormManager {

    fun closeScope() {
        val scope = getKoin().getScopeOrNull(ScopeName)
        scope?.close()
    }

    fun getForm(): CreateInvoiceForm {
        val scope = getKoin().getScopeOrNull(ScopeName)
        if (scope == null) {
            val newScope = getKoin().createScope(ScopeName, ScopeQualifier)
            newScope.declare(CreateInvoiceForm())
            return newScope.get<CreateInvoiceForm>()
        }

        return scope.get<CreateInvoiceForm>()
    }

    companion object {
        const val ScopeName = "CreateInvoiceFormManager"
        val ScopeQualifier = named("CreateInvoiceFormManager")
    }
}
