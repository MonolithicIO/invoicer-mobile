package io.github.alaksion.invoicer.features.company.data.repository

import io.github.alaksion.invoicer.features.company.data.datasource.CompanyRemoteDataSource
import io.github.alaksion.invoicer.features.company.data.model.CreateCompanyAddressRequest
import io.github.alaksion.invoicer.features.company.data.model.CreateCompanyPaymentAccountRequest
import io.github.alaksion.invoicer.features.company.data.model.CreateCompanyRequest
import io.github.alaksion.invoicer.features.company.data.model.toModel
import io.github.alaksion.invoicer.features.company.domain.model.CompanyDetailsModel
import io.github.alaksion.invoicer.features.company.domain.model.CreateCompanyModel
import io.github.alaksion.invoicer.features.company.domain.model.ListCompaniesItemModel
import io.github.alaksion.invoicer.features.company.domain.model.ListCompaniesModel
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository

internal class CompanyRepositoryImpl(
    private val dataSource: CompanyRemoteDataSource
) : CompanyRepository {

    override suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesModel {
        val response = dataSource.listCompanies(
            page = page,
            limit = limit
        )

        return ListCompaniesModel(
            companies = response.companies.map { item ->
                ListCompaniesItemModel(
                    document = item.document,
                    name = item.name,
                    id = item.id
                )
            },
            total = response.total,
            nextPageIndex = response.nextPageIndex
        )
    }

    override suspend fun createCompany(data: CreateCompanyModel): String {
        return dataSource.createCompany(
            data = CreateCompanyRequest(
                name = data.name,
                document = data.document,
                address = CreateCompanyAddressRequest(
                    addressLine1 = data.address.addressLine1,
                    addressLine2 = data.address.addressLine2,
                    city = data.address.city,
                    state = data.address.state,
                    postalCode = data.address.postalCode,
                    countryCode = data.address.countryCode
                ),
                paymentAccount = CreateCompanyPaymentAccountRequest(
                    iban = data.paymentAccount.iban,
                    swift = data.paymentAccount.swift,
                    bankName = data.paymentAccount.bankName,
                    bankAddress = data.paymentAccount.bankAddress
                ),
                intermediaryAccount = data.intermediaryAccount?.let { intermediary ->
                    CreateCompanyPaymentAccountRequest(
                        iban = intermediary.iban,
                        swift = intermediary.swift,
                        bankName = intermediary.bankName,
                        bankAddress = intermediary.bankAddress
                    )
                }
            )
        )
    }

    override suspend fun companyDetails(companyId: String): CompanyDetailsModel {
        return dataSource.details(companyId).toModel()
    }
}
