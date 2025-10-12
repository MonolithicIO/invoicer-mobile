package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.payaccount

import cafe.adriel.voyager.core.model.ScreenModel
import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet

internal class PayAccountScreenModel(
    private val form: CreateCompanyForm
) : ScreenModel {

    private val _state = MutableStateFlow(PayAccountState())
    val state = _state.asStateFlow()

    fun resumeState() {
        _state.update {
            it.copy(
                primaryIban = form.primaryPayAccount.iban,
                primarySwift = form.primaryPayAccount.swift,
                primaryBankName = form.primaryPayAccount.bankName,
                primaryBankAddress = form.primaryPayAccount.bankAddress,
                useIntermediaryAccount = form.intermediaryPayAccount != null
            )
        }
    }

    fun onChangePrimaryIban(iban: String) {
        _state.updateAndGet {
            it.copy(
                primaryIban = iban.trim()
            )
        }.let { newState ->
            form.primaryPayAccount = form.primaryPayAccount.copy(iban = newState.primaryIban)
        }
    }

    fun onChangePrimarySwift(swift: String) {
        _state.updateAndGet {
            it.copy(
                primarySwift = swift.trim()
            )
        }.let { newState ->
            form.primaryPayAccount = form.primaryPayAccount.copy(swift = newState.primarySwift)
        }
    }

    fun onChangePrimaryBankName(bankName: String) {
        _state.updateAndGet {
            it.copy(
                primaryBankName = bankName.trim()
            )
        }.let { newState ->
            form.primaryPayAccount =
                form.primaryPayAccount.copy(bankName = newState.primaryBankName)
        }
    }

    fun onChangePrimaryBankAddress(bankAddress: String) {
        _state.updateAndGet {
            it.copy(
                primaryBankAddress = bankAddress.trim()
            )
        }.let { newState ->
            form.primaryPayAccount =
                form.primaryPayAccount.copy(bankAddress = newState.primaryBankAddress)
        }
    }

    fun toggleIntermediaryAccount() {
        _state.updateAndGet {
            it.copy(
                useIntermediaryAccount = it.useIntermediaryAccount.not()
            )
        }.let { newState ->
            if (newState.useIntermediaryAccount) {
                form.useIntermediaryAccount = true
            } else {
                form.intermediaryPayAccount = null
                form.useIntermediaryAccount = false
            }
        }
    }
}