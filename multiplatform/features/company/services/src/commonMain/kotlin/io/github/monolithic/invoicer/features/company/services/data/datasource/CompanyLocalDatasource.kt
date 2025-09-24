package io.github.monolithic.invoicer.features.company.services.data.datasource

import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage

interface CompanyLocalDatasource {
    suspend fun storeSelectedCompanyId(companyId: String)
    suspend fun getSelectedCompanyId(): String?
}

internal class CompanyLocalDatasourceImpl(
    private val storage: LocalStorage
) : CompanyLocalDatasource {

    override suspend fun storeSelectedCompanyId(companyId: String) {
        storage.setString(
            value = companyId,
            key = SELECTED_COMPANY_ID_KEY
        )
    }

    override suspend fun getSelectedCompanyId(): String? {
        return storage.getString(SELECTED_COMPANY_ID_KEY)
    }

    companion object {
        private const val SELECTED_COMPANY_ID_KEY = "selected_company_id"
    }
}
