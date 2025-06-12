package io.github.alaksion.invoicer.features.company.presentation.screens.create.steps.payaccount.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import io.github.alaksion.invoicer.foundation.designSystem.components.InputField
import io.github.alaksion.invoicer.foundation.designSystem.tokens.Spacing

internal data class CompanyPayInfoFormCallbacks(
    val onChangeIban: (String) -> Unit,
    val onChangeSwift: (String) -> Unit,
    val onChangeBankName: (String) -> Unit,
    val onChangeBankAddress: (String) -> Unit,
)

@Composable
internal fun CompanyPayInfoForm(
    iban: String,
    swift: String,
    bankName: String,
    bankAddress: String,
    callbacks: CompanyPayInfoFormCallbacks,
    modifier: Modifier = Modifier,
    extraContent: (@Composable ColumnScope.() -> Unit)? = null
) {
    val (swiftRef, ibanRef, bankNameRef, bankAddressRef) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.medium)
    ) {
        InputField(
            value = swift,
            onValueChange = callbacks.onChangeSwift,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(swiftRef),
            keyboardActions = KeyboardActions(
                onNext = { ibanRef.requestFocus() }
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            )
        )

        InputField(
            value = iban,
            onValueChange = callbacks.onChangeIban,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(ibanRef),
            keyboardActions = KeyboardActions(
                onNext = { bankNameRef.requestFocus() }
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            )
        )

        InputField(
            value = bankName,
            onValueChange = callbacks.onChangeIban,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(bankNameRef),
            keyboardActions = KeyboardActions(
                onNext = { bankAddressRef.requestFocus() }
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Next
            )
        )

        InputField(
            value = bankAddress,
            onValueChange = callbacks.onChangeBankAddress,
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(bankAddressRef),
            keyboardActions = KeyboardActions(
                onDone = { keyboardController?.hide() }
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Characters,
                autoCorrectEnabled = false,
                imeAction = ImeAction.Done
            )
        )

        extraContent?.let { it() }
    }
}