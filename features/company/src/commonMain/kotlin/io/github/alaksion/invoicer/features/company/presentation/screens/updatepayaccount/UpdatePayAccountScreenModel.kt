package io.github.alaksion.invoicer.features.company.presentation.screens.updatepayaccount

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import io.github.alaksion.invoicer.features.company.domain.model.UpdatePayAccountModel
import io.github.alaksion.invoicer.features.company.domain.repository.PayAccountRepository
import io.github.alaksion.invoicer.foundation.network.request.handle
import io.github.alaksion.invoicer.foundation.network.request.launchRequest
import io.github.alaksion.invoicer.foundation.session.Session
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class UpdatePayAccountScreenModel(
    private val repository: PayAccountRepository,
    private val dispatcher: CoroutineDispatcher,
    private val session: Session
) : ScreenModel {

    private val _state = MutableStateFlow(UpdatePayAccountState())
    val state = _state.asStateFlow()

    private val _events = MutableSharedFlow<UpdatePayAccountEvent>()
    val event = _events.asSharedFlow()

    fun initState(
        args: UpdatePayAccountScreenArgs
    ) {
        _state.update {
            it.copy(
                swift = args.swift,
                iban = args.iban,
                bankAddress = args.bankAddress,
                bankName = args.bankName,
                accountType = args.accountType,
            )
        }
    }

    fun updateSwift(swift: String) {
        _state.update { it.copy(swift = swift) }
    }

    fun updateIban(iban: String) {
        _state.update { it.copy(iban = iban) }
    }

    fun updateBankAddress(bankAddress: String) {
        _state.update { it.copy(bankAddress = bankAddress) }
    }

    fun updateBankName(bankName: String) {
        _state.update { it.copy(bankName = bankName) }
    }

    fun updatePayAccount(
        payAccountId: String,
    ) {
        screenModelScope.launch(dispatcher) {
            launchRequest {
                repository.updatePayAccount(
                    request = UpdatePayAccountModel(
                        swift = state.value.swift,
                        iban = state.value.iban,
                        bankAddress = state.value.bankAddress,
                        bankName = state.value.bankName
                    ),
                    companyId = session.getCompany().id,
                    payAccountId = payAccountId
                )
            }.handle(
                onStart = {
                    _state.update { it.copy(isButtonLoading = true) }
                },
                onFinish = {
                    _state.update { it.copy(isButtonLoading = false) }
                },
                onSuccess = {
                    _events.emit(UpdatePayAccountEvent.Success)
                },
                onFailure = {
                    _events.emit(UpdatePayAccountEvent.Failure(it.message.orEmpty()))
                }
            )
        }
    }
}