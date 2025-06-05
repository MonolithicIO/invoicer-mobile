package io.github.alaksion.invoicer.features.company.presentation.selectcompany

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.company.domain.model.ListCompaniesItemModel
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
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
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _state = MutableStateFlow(SelectCompanyState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<SelectCompanyEvent>()
    val events = _events.asSharedFlow()

    fun loadCompanies() {
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
                onSuccess = { result -> handleCompaniesResponse(result.companies) }
            )
        }
    }

    private fun handleCompaniesResponse(
        companies: List<ListCompaniesItemModel>
    ) {
        if (companies.isEmpty()) {
            _state.update { it.copy(mode = SelectCompanyMode.CreateCompany) }
            return
        }

        if (companies.size == 1) {
            // Set company info and continue to home
            screenModelScope.launch(dispatcher) {
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
