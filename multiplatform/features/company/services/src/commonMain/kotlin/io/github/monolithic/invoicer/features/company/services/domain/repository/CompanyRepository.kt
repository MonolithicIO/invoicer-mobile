package io.github.monolithic.invoicer.features.company.services.domain.repository

import io.github.monolithic.invoicer.features.company.services.domain.model.CompanyDetailsModel
import io.github.monolithic.invoicer.features.company.services.domain.model.CreateCompanyModel
import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesModel
import io.github.monolithic.invoicer.features.company.services.domain.model.UpdateAddressModel

interface CompanyRepository {
    suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesModel

    suspend fun createCompany(
        data: CreateCompanyModel
    ): String

    suspend fun companyDetails(
        companyId: String
    ): CompanyDetailsModel

    suspend fun updateAddress(
        companyId: String,
        model: UpdateAddressModel
    )

    suspend fun storeSelectedCompanyId(
        companyId: String
    )

    suspend fun getSelectedCompanyId(): String?
}
