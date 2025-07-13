package io.github.alaksion.invoicer.features.company.presentation.screens.updatepayaccount

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.alaksion.invoicer.features.company.domain.repository.PayAccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

internal class UpdatePayAccountScreenModel(
    private val repository: PayAccountRepository,
    private val dispatcher: CoroutineDispatcher
) : ScreenModel {

    private val _state = MutableStateFlow(UpdatePayAccountState())
    val state = _state.asStateFlow()

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
                mode = UpdatePayAccountMode.Content,
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
}