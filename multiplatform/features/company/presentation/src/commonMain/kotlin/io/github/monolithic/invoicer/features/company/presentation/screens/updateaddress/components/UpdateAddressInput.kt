package io.github.monolithic.invoicer.features.company.presentation.screens.updateaddress.components

import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.text.input.ImeAction
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOutlinedInput

@Composable
internal fun UpdateAddressInput(
    value: String,
    focusRef: FocusRequester,
    imeAction: UpdateAddressInputIme,
    onChange: (String) -> Unit,
    onImeAction: () -> Unit,
    label: String,
    supportText: String? = null,
    modifier: Modifier = Modifier
) {
    val keyboardAction = remember(onImeAction, imeAction) {
        if (imeAction == UpdateAddressInputIme.Next) {
            KeyboardActions(
                onNext = { onImeAction() }
            )
        } else {
            KeyboardActions(
                onDone = { onImeAction() }
            )
        }
    }

    val keyboardOptions = remember(imeAction) {
        KeyboardOptions(
            imeAction = when (imeAction) {
                UpdateAddressInputIme.Next -> ImeAction.Next
                UpdateAddressInputIme.Done -> ImeAction.Done
            },
            autoCorrectEnabled = false,
        )
    }

    InkOutlinedInput(
        value = value,
        onValueChange = onChange,
        modifier = modifier.focusRequester(focusRef),
        keyboardActions = keyboardAction,
        keyboardOptions = keyboardOptions,
        label = label,
        supportText = supportText
    )
}

internal enum class UpdateAddressInputIme {
    Next, Done;
}
