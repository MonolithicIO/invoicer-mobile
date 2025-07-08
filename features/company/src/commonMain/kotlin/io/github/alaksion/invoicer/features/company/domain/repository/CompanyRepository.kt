package io.github.alaksion.invoicer.features.company.domain.repository

import io.github.alaksion.invoicer.features.company.domain.model.CompanyDetailsModel
import io.github.alaksion.invoicer.features.company.domain.model.CreateCompanyModel
import io.github.alaksion.invoicer.features.company.domain.model.ListCompaniesModel

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
}
