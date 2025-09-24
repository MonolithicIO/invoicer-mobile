package io.github.monolithic.invoicer.features.company.services.data.repository

import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyLocalDatasource
import io.github.monolithic.invoicer.features.company.services.data.datasource.CompanyRemoteDataSource
import io.github.monolithic.invoicer.features.company.services.data.model.CreateCompanyAddressRequest
import io.github.monolithic.invoicer.features.company.services.data.model.CreateCompanyPaymentAccountRequest
import io.github.monolithic.invoicer.features.company.services.data.model.CreateCompanyRequest
import io.github.monolithic.invoicer.features.company.services.data.model.UpdateAddressRequest
import io.github.monolithic.invoicer.features.company.services.data.model.toModel
import io.github.monolithic.invoicer.features.company.services.domain.model.CompanyDetailsModel
import io.github.monolithic.invoicer.features.company.services.domain.model.CreateCompanyModel
import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesItemModel
import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesModel
import io.github.monolithic.invoicer.features.company.services.domain.model.UpdateAddressModel
import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository

internal class CompanyRepositoryImpl(
    private val remoteDatasource: CompanyRemoteDataSource,
    private val localDatasource: CompanyLocalDatasource,
) : CompanyRepository {

    override suspend fun listCompanies(
        page: Int,
        limit: Int
    ): ListCompaniesModel {
        val response = remoteDatasource.listCompanies(
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
        return remoteDatasource.createCompany(
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
        return remoteDatasource.details(companyId).toModel()
    }

    override suspend fun updateAddress(companyId: String, model: UpdateAddressModel) {
        return remoteDatasource.updateAddres(
            companyId = companyId,
            request = UpdateAddressRequest(
                addressLine = model.addressLine,
                addressLine2 = model.addressLine2,
                city = model.city,
                state = model.state,
                postalCode = model.postalCode
            )
        )
    }

    override suspend fun storeSelectedCompanyId(companyId: String) {
        localDatasource.storeSelectedCompanyId(companyId = companyId)
    }

    override suspend fun getSelectedCompanyId(): String? {
        return localDatasource.getSelectedCompanyId()
    }
}
