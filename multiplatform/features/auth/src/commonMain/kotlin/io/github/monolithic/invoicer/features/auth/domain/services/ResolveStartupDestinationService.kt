package io.github.monolithic.invoicer.features.auth.domain.services

import io.github.monolithic.invoicer.features.auth.domain.model.StartupDestination
import io.github.monolithic.invoicer.features.company.services.domain.model.CompanyDetailsModel
import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.features.company.services.domain.service.SelectCompanyService
import io.github.monolithic.invoicer.foundation.auth.domain.repository.AuthTokenRepository

internal interface ResolveStartupDestinationService {
    suspend fun resolveDestination(): StartupDestination
}

internal class ResolveStartupDestinationServiceImpl(
    private val authTokenRepository: AuthTokenRepository,
    private val companyRepository: CompanyRepository,
    private val selectCompanyService: SelectCompanyService
) : ResolveStartupDestinationService {
    override suspend fun resolveDestination(): StartupDestination {

        if (!checkAuthTokenPresence()) {
            return StartupDestination.AuthMenu
        }

        val selectedCompanyId =
            companyRepository.getSelectedCompanyId() ?: return StartupDestination.SelectCompany

        val company = getSelectedCompanyDetails(
            companyId = selectedCompanyId
        ) ?: return StartupDestination.SelectCompany

        selectCompanyService.select(
            companyName = company.name,
            companyId = company.id
        )

        return StartupDestination.Home
    }

    private suspend fun checkAuthTokenPresence(): Boolean {
        return authTokenRepository.getAuthTokens() != null
    }

    private suspend fun getSelectedCompanyDetails(
        companyId: String
    ): CompanyDetailsModel? {
        return runCatching {
            companyRepository.companyDetails(companyId = companyId)
        }.fold(
            onSuccess = { it },
            onFailure = { null }
        )
    }

}
