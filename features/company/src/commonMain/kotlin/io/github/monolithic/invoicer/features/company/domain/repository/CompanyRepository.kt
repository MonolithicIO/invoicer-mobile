package io.github.monolithic.invoicer.features.company.domain.repository

import io.github.monolithic.invoicer.features.company.domain.model.CompanyDetailsModel
import io.github.monolithic.invoicer.features.company.domain.model.CreateCompanyModel
import io.github.monolithic.invoicer.features.company.domain.model.ListCompaniesModel
import io.github.monolithic.invoicer.features.company.domain.model.UpdateAddressModel

internal interface CompanyRepository {
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
}
