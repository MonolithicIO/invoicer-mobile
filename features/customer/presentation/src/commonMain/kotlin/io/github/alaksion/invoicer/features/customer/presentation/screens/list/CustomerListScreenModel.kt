package io.github.alaksion.invoicer.features.customer.presentation.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.customer.domain.model.CustomerListModel
import io.github.alaksion.invoicer.features.customer.domain.repository.CustomerRepository
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CustomerListScreenModel(
    private val customerRepository: CustomerRepository,
    private val dispatcher: CoroutineDispatcher,
    private val session: Session
) : ScreenModel {

    private var currentPage: Long? = 0L

    private val _state = MutableStateFlow(CustomerListState())
    val state = _state.asStateFlow()

    fun loadPage() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                getCustomerList(
                    page = START_PAGE
                )
            }.handle(
                onStart = {
                    _state.update {
                        it.copy(mode = CustomerListMode.Loading)
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(mode = CustomerListMode.Error)
                    }
                },
                onSuccess = { response ->
                    _state.update { currentState ->
                        currentState.copy(
                            customers = (currentState.customers + response.items)
                                .toPersistentList(),
                            mode = CustomerListMode.Content
                        )
                    }
                    currentPage = response.nextPage
                }
            )
        }
    }

    private suspend fun getCustomerList(
        page: Long
    ): CustomerListModel = customerRepository.listCustomers(
        companyId = session.company.id,
        page = page,
        pageSize = PAGE_SIZE
    )


    companion object {
        const val PAGE_SIZE = 20L
        const val START_PAGE = 0L
    }

}