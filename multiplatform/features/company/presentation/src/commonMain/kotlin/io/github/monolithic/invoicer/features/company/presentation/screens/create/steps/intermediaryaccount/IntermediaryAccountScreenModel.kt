package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.intermediaryaccount

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

internal class IntermediaryAccountScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(IntermediaryAccountState())
    val state = _state.asStateFlow()

    fun resumeState() {
        _state.update {
            it.copy(
                primaryIban = form.intermediaryPayAccount?.iban.orEmpty(),
                primarySwift = form.intermediaryPayAccount?.swift.orEmpty(),
                primaryBankName = form.intermediaryPayAccount?.bankName.orEmpty(),
                primaryBankAddress = form.intermediaryPayAccount?.bankAddress.orEmpty(),
            )
        }
    }

    fun onChangePrimaryIban(iban: String) {
        _state.updateAndGet {
            it.copy(
                primaryIban = iban.trim()
            )
        }.let { newState ->
            form.intermediaryPayAccount =
                form.intermediaryPayAccount?.copy(iban = newState.primaryIban)
        }
    }

    fun onChangePrimarySwift(swift: String) {
        _state.updateAndGet {
            it.copy(
                primarySwift = swift.trim()
            )
        }.let { newState ->
            form.intermediaryPayAccount =
                form.intermediaryPayAccount?.copy(swift = newState.primarySwift)
        }
    }

    fun onChangePrimaryBankName(bankName: String) {
        _state.updateAndGet {
            it.copy(
                primaryBankName = bankName.trim()
            )
        }.let { newState ->
            form.intermediaryPayAccount =
                form.intermediaryPayAccount?.copy(bankName = newState.primaryBankName)
        }
    }

    fun onChangePrimaryBankAddress(bankAddress: String) {
        _state.updateAndGet {
            it.copy(
                primaryBankAddress = bankAddress.trim()
            )
        }.let { newState ->
            form.intermediaryPayAccount =
                form.intermediaryPayAccount?.copy(bankAddress = newState.primaryBankAddress)
        }
    }
}