package io.github.monolithic.invoicer.features.company.services.data.datasource

import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage
import io.github.monolithic.invoicer.foundation.platform.storage.StorageKeys

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
            key = StorageKeys.SelectedCompany.key
        )
    }

    override suspend fun getSelectedCompanyId(): String? {
        return storage.getString(StorageKeys.SelectedCompany.key)
    }
}
