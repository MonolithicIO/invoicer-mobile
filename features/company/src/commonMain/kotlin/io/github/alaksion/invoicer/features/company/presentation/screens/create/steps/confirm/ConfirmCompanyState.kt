package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.confirm

import io.github.alaksion.invoicer.features.company.domain.model.CreateCompanyAddressModel
import io.github.alaksion.invoicer.features.company.domain.model.CreateCompanyModel
import io.github.alaksion.invoicer.features.company.domain.model.CreateCompanyPaymentAccountModel
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm.IntermediaryAccountInfo
import io.github.alaksion.invoicer.features.company.presentation.model.CreateCompanyForm.PayAccountInfo

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
        shouldContinueToIntermediary = false
    ),
    val intermediaryPayAccount: IntermediaryAccountInfo? = null,
    val isButtonLoading: Boolean = false,
    val isButtonEnabled: Boolean = false
)

internal sealed interface CreateCompanyEvents {
    data object Success : CreateCompanyEvents
    data class Error(val message: String) : CreateCompanyEvents
}

internal fun ConfirmCompanyState.toRequestModel() = CreateCompanyModel(
    name = companyName,
    document = companyDocument,
    address = CreateCompanyAddressModel(
        addressLine1 = addressLine1,
        addressLine2 = addressLine2.ifBlank { null },
        city = city,
        state = state,
        postalCode = postalCode,
        countryCode = countryCode
    ),
    paymentAccount = CreateCompanyPaymentAccountModel(
        iban = primaryPayAccount.iban,
        swift = primaryPayAccount.swift,
        bankName = primaryPayAccount.bankName,
        bankAddress = primaryPayAccount.bankAddress
    ),
    intermediaryAccount = intermediaryPayAccount?.let { account ->
        CreateCompanyPaymentAccountModel(
            iban = account.iban,
            swift = account.swift,
            bankName = account.bankName,
            bankAddress = account.bankAddress
        )
    }
)
