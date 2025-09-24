package io.github.monolithic.invoicer.features.company.presentation.screens.select

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.company.services.domain.model.ListCompaniesItemModel
import io.github.monolithic.invoicer.features.company.services.domain.repository.CompanyRepository
import io.github.monolithic.invoicer.features.company.services.domain.service.SelectCompanyService
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SelectCompanyScreenModel(
    private val repository: CompanyRepository,
    private val dispatcher: CoroutineDispatcher,
    private val selectCompanyService: SelectCompanyService
) : ScreenModel {

    private val _state = MutableStateFlow(SelectCompanyState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<SelectCompanyEvent>()
    val events = _events.asSharedFlow()

    fun loadCompanies(
        autoSelectFirst: Boolean
    ) {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                repository.listCompanies(
                    page = 0,
                    limit = 50
                )
            }.handle(
                onStart = {
                    _state.update { it.copy(mode = SelectCompanyMode.Loading) }
                },
                onFailure = {
                    _state.update { it.copy(mode = SelectCompanyMode.Error) }
                },
                onSuccess = { result ->
                    handleCompaniesResponse(
                        result.companies,
                        shouldAutoSelectFirst = autoSelectFirst
                    )
                }
            )
        }
    }

    fun selectCompany(companyId: String) {
        screenModelScope.launch(dispatcher) {
            val company = state.value.companies.first { it.id == companyId }
            selectCompanyService.select(
                companyName = company.name,
                companyId = companyId
            )
            _events.emit(SelectCompanyEvent.ContinueToHome)
        }
    }

    private suspend fun handleCompaniesResponse(
        companies: List<ListCompaniesItemModel>,
        shouldAutoSelectFirst: Boolean
    ) {
        if (companies.isEmpty()) {
            _state.update { it.copy(mode = SelectCompanyMode.CreateCompany) }
            return
        }

        if (companies.size == 1) {
            selectCompanyService.select(
                companyName = companies.first().name,
                companyId = companies.first().id
            )

            if (shouldAutoSelectFirst) {
                _events.emit(SelectCompanyEvent.ContinueToHome)
            }

            return
        }

        _state.update {
            it.copy(
                mode = SelectCompanyMode.List,
                companies = companies.toImmutableList()
            )
        }
    }
}
