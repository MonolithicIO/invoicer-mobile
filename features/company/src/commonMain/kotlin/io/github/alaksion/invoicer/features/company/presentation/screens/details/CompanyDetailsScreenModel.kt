package io.github.alaksion.invoicer.features.company.presentation.screens.details

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.company.domain.repository.CompanyRepository
import io.github.alaksion.invoicer.features.company.presentation.model.toUiModel
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class CompanyDetailsScreenModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: CompanyRepository,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(CompanyDetailsState())
    val state = _state.asStateFlow()

    fun initState() {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                repository.companyDetails(
                    companyId = session.getCompany().id
                )
            }.handle(
                onStart = { _state.update { it.copy(mode = CompanyDetailsMode.Loading) } },
                onFailure = { _state.update { it.copy(mode = CompanyDetailsMode.Error) } },
                onSuccess = { response ->
                    _state.update {
                        it.copy(
                            name = response.name,
                            document = response.document,
                            addressLine1 = response.address.addressLine1,
                            addressLine2 = response.address.addressLine2,
                            city = response.address.city,
                            state = response.address.state,
                            postalCode = response.address.postalCode,
                            countryCode = response.address.countryCode,
                            payAccount = response.paymentAccount.toUiModel(),
                            intermediaryAccount = response.intermediaryAccount?.toUiModel(),
                            mode = CompanyDetailsMode.Content
                        )
                    }
                }
            )
        }
    }
}
