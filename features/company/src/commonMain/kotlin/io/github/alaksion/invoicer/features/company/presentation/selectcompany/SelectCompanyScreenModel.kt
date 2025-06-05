package io.github.alaksion.invoicer.features.company.presentation.selectcompany

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.company.domain.model.ListCompaniesItemModel
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class SelectCompanyScreenModel(
    private val repository: CompanyRepository,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _state = MutableStateFlow(SelectCompanyState())
    val state = _state.asStateFlow()

    fun loadCompanies() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                repository.listCompanies(
                    page = 0,
                    limit = 50
                )
            }.handle(
                onStart = {
                    _state.update {
                        it.copy(mode = SelectCompanyMode.Loading)
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(mode = SelectCompanyMode.Error)
                    }
                },
                onSuccess = {

                }
            )
        }
    }

    private fun handleCompaniesResponse(
        companies: List<ListCompaniesItemModel>
    ) {

    }
}