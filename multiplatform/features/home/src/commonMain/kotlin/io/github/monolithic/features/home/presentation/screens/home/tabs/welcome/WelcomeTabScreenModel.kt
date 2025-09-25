package io.github.monolithic.features.home.presentation.screens.home.tabs.welcome

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.monolithic.features.home.domain.services.GetHomeDetailsService
import io.github.monolithic.features.home.presentation.screens.home.tabs.welcome.components.LatestInvoiceUiModel
import io.github.monolithic.invoicer.foundation.auth.session.Session
import io.github.monolithic.invoicer.foundation.network.request.handle
import io.github.monolithic.invoicer.foundation.network.request.launchRequest
import io.github.monolithic.invoicer.foundation.utils.date.defaultFormat
import io.github.monolithic.invoicer.foundation.utils.money.moneyFormat
import io.github.monolithic.invoicer.foundation.watchers.bus.feature.HomeRefreshBus
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class WelcomeTabScreenModel(
    private val session: Session,
    private val homeDetailsService: GetHomeDetailsService,
    private val dispatchers: CoroutineDispatcher,
    private val homeRefreshBus: HomeRefreshBus
) : ScreenModel {

    private val _state = MutableStateFlow(WelcomeTabState())
    val state = _state.asStateFlow()

    init {
        screenModelScope.launch(dispatchers) {
            homeRefreshBus.subscribe().collectLatest { loadData() }
        }
    }

    fun loadData() {
        screenModelScope.launch(dispatchers) {
            launchRequest {
                homeDetailsService.get()
            }.handle(
                onSuccess = { response ->
                    _state.update {
                        it.copy(
                            companyName = session.getCompany().name,
                            latestInvoices = response.top3Invoices.map { invoice ->
                                LatestInvoiceUiModel(
                                    companyName = invoice.customerName,
                                    amount = invoice.totalAmount.moneyFormat(),
                                    timeStamp = invoice.createdAt.defaultFormat()
                                )
                            }.toPersistentList(),
                            mode = WelcomeTabMode.Content
                        )
                    }
                },
                onFailure = {
                    _state.update {
                        it.copy(mode = WelcomeTabMode.Error)
                    }
                },
                onStart = {
                    _state.update {
                        it.copy(mode = WelcomeTabMode.Loading)
                    }
                }
            )
        }
        _state.value = WelcomeTabState(
            companyName = session.getCompany().name,
        )
    }
}
