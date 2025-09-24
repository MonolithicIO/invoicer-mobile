package io.github.monolithic.invoicer.features.company.services.domain.service

import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.foundation.auth.session.SessionCompany
import io.github.monolithic.invoicer.foundation.auth.session.SessionUpdater

interface SelectCompanyService {
    suspend fun select(
        companyName: String,
        companyId: String
    )
}

internal class SelectCompanyServiceImpl(
    private val sessionUpdater: SessionUpdater,
    private val companyRepository: CompanyRepository
) : SelectCompanyService {

    override suspend fun select(companyName: String, companyId: String) {
        companyRepository.storeSelectedCompanyId(companyId = companyId)
        sessionUpdater.updateCompany(
            company = SessionCompany(
                name = companyName,
                id = companyId
            )
        )
    }
}
