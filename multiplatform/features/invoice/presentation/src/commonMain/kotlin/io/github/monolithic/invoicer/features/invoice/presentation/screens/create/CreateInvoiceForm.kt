package io.github.monolithic.invoicer.features.invoice.presentation.screens.create

import io.github.monolithic.invoicer.features.invoice.presentation.screens.create.steps.activities.model.CreateInvoiceActivityUiModel
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin

internal class CreateInvoiceForm(
    clock: Clock
) {

    private val today = clock.now().toLocalDateTime(TimeZone.currentSystemDefault()).date

    var invoiceNumber: String = ""
    var customerId: String = ""
    var customerName: String = ""
    var issueDate: LocalDate = today
    var dueDate: LocalDate = today

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
            newScope.declare(
                CreateInvoiceForm(
                    clock = getKoin().get()
                )
            )
            return newScope.get<CreateInvoiceForm>()
        }

        return scope.get<CreateInvoiceForm>()
    }

    companion object {
        const val ScopeName = "CreateInvoiceFormManager"
        val ScopeQualifier = named("CreateInvoiceFormManager")
    }
}
