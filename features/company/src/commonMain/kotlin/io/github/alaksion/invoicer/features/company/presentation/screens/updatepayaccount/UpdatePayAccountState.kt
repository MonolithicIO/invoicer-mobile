package io.github.alaksion.invoicer.features.company.presentation.screens.updatepayaccount

internal data class UpdatePayAccountState(
    val swift: String = "",
    val iban: String = "",
    val bankAddress: String = "",
    val bankName: String = "",
    val accountType: UpdatePayAccountScreenArgs.AccountType =
        UpdatePayAccountScreenArgs.AccountType.Primary,
    val isButtonLoading: Boolean = false,
) {
    val isButtonEnabled =
        swift.isNotBlank() &&
                iban.isNotBlank() &&
                bankAddress.isNotBlank() &&
                bankName.isNotBlank()

    val isDeleteButtonVisible = accountType == UpdatePayAccountScreenArgs.AccountType.Intermediary
}

internal sealed interface UpdatePayAccountEvent {
    data class Failure(val message: String) : UpdatePayAccountEvent
    data object Success : UpdatePayAccountEvent
}
