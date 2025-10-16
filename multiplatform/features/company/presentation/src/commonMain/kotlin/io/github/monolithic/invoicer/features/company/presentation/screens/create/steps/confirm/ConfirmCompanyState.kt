package io.github.monolithic.invoicer.features.company.presentation.screens.create.steps.confirm

import io.github.monolithic.invoicer.features.company.presentation.model.CreateCompanyForm.PayAccountInfo
import io.github.monolithic.invoicer.features.company.services.domain.model.CreateCompanyAddressModel
import io.github.monolithic.invoicer.features.company.services.domain.model.CreateCompanyModel
import io.github.monolithic.invoicer.features.company.services.domain.model.CreateCompanyPaymentAccountModel

internal data class ConfirmCompanyState(
    val companyName: String = "",
    val companyDocument: String = "",
    val addressLine1: String = "",
    val addressLine2: String = "",
    val city: String = "",
    val state: String = "",
    val postalCode: String = "",
    val countryCode: String = "",
    val primaryPayAccount: PayAccountInfo = PayAccountInfo(
        iban = "",
        swift = "",
        bankName = "",
        bankAddress = "",
    ),
    val intermediaryPayAccount: PayAccountInfo? = null,
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)

internal sealed interface CreateCompanyEvents {
    data object Success : CreateCompanyEvents
    data class Error(val message: String) : CreateCompanyEvents
}

internal fun ConfirmCompanyState.toRequestModel() = CreateCompanyModel(
    name = companyName.trim(),
    document = companyDocument.trim(),
    address = CreateCompanyAddressModel(
        addressLine1 = addressLine1.trim(),
        addressLine2 = addressLine2.ifBlank { null }?.trim(),
        city = city.trim(),
        state = state.trim(),
        postalCode = postalCode.trim(),
        countryCode = countryCode.trim()
    ),
    paymentAccount = CreateCompanyPaymentAccountModel(
        iban = primaryPayAccount.iban.trim(),
        swift = primaryPayAccount.swift.trim(),
        bankName = primaryPayAccount.bankName.trim(),
        bankAddress = primaryPayAccount.bankAddress.trim()
    ),
    intermediaryAccount = intermediaryPayAccount?.let { account ->
        CreateCompanyPaymentAccountModel(
            iban = account.iban.trim(),
            swift = account.swift.trim(),
            bankName = account.bankName.trim(),
            bankAddress = account.bankAddress.trim()
        )
    }
)
