package io.github.alaksion.invoicer.features.company.presentation.model

import org.koin.core.qualifier.named
import org.koin.mp.KoinPlatform.getKoin

class CreateCompanyForm {
    var companyName: String = ""
    var companyDocument: String = ""
}

internal class CreateCompanyFormManager {

    fun closeScope() {
        val scope = getKoin().getScopeOrNull(ScopeName)
        scope?.close()
    }

    fun getForm(): CreateCompanyForm {
        val currentScope = getKoin().getScopeOrNull(ScopeName)

        if (currentScope == null) {
            val newScope = getKoin().createScope(ScopeName, ScopeQualifier)
            newScope.declare(CreateCompanyForm())
            return newScope.get<CreateCompanyForm>()
        }

        return currentScope.get<CreateCompanyForm>()
    }

    companion object {
        const val ScopeName = "CreateCompanyFlowScope"
        val ScopeQualifier = named("CreateCompanyFlowScope")
    }
}