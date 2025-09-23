package io.github.monolithic.invoicer.features.customer.presentation.screens.list

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.invoicer.features.customer.domain.model.CustomerListModel
import io.github.monolithic.invoicer.features.customer.domain.repository.CustomerRepository
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.session.Session
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
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

    private val _events = MutableSharedFlow<CustomerListEvent>()
    val events = _events.asSharedFlow()

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
                    println(it)
                    _state.update {
                        it.copy(mode = CustomerListMode.Error)
                    }
                },
                onSuccess = { response ->
                    _state.update { currentState ->
                        currentState.copy(
                            customers = response.items.toPersistentList(),
                            mode = CustomerListMode.Content
                        )
                    }
                    currentPage = response.nextPage
                }
            )
        }
    }

    fun nextPage() {
        if (state.value.nextPageLoading) return

        currentPage?.let { page ->
            screenModelScope.launch(dispatcher) {
                launchRequest {
                    getCustomerList(page)
                }.handle(
                    onStart = {
                        _state.update {
                            it.copy(nextPageLoading = true)
                        }
                    },
                    onFinish = {
                        _state.update {
                            it.copy(nextPageLoading = false)
                        }
                    },
                    onFailure = {
                        _events.emit(CustomerListEvent.NextPageFailed)
                    },
                    onSuccess = {
                        _state.update {
                            it.copy(
                                customers = (it.customers + it.customers).toPersistentList()
                            )
                        }
                    }
                )
            }
        }
    }

    private suspend fun getCustomerList(
        page: Long
    ): CustomerListModel = customerRepository.listCustomers(
        companyId = session.getCompany().id,
        page = page,
        pageSize = PAGE_SIZE
    )


    companion object {
        const val PAGE_SIZE = 20L
        const val START_PAGE = 0L
    }
}
