package io.github.alaksion.invoicer.features.company.presentation.model

import org.koin.core.qualifier.named
import org.koin.core.scope.Scope
import org.koin.mp.KoinPlatform.getKoin

class CreateCompanyForm {
    var companyName: String = ""
    var companyDocument: String = ""
}

internal class CreateCompanyFormManager {

    private var scope: Scope? = null

    fun startScope() {
        scope = getKoin().createScope(
            scopeId = ScopeName,
            qualifier = ScopeQualifier
        )
    }

    fun closeScope() {
        scope?.close()
        scope = null
    }

    fun getForm(): CreateCompanyForm {
        return scope?.get()!!
    }

    companion object {
        const val ScopeName = "CreateCompanyFlowScope"
        val ScopeQualifier = named("CreateCompanyFormManager")
    }
}