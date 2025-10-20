package io.github.monolithic.invoicer.foundation.designSystem.ink.previews

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.monolithic.invoicer.foundation.designSystem.ink.PreviewContainer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.button.InkPrimaryButton
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOtpInput

@Composable
@Preview
private fun OtpInputPreview() {
    PreviewContainer(true) {
        var state by remember { mutableStateOf("") }
        var error by remember { mutableStateOf(false) }
        val interactionSource = remember { MutableInteractionSource() }
        val focused by interactionSource.collectIsFocusedAsState()



        LaunchedEffect(focused) {
            println("state: $focused")
        }

        InkOtpInput(
            interactionSource = interactionSource,
            value = state,
            digitCount = 2,
            modifier = Modifier.fillMaxWidth(),
            onValueChange = {
                state = it.trim()
            },
            isError = error,
            onFinalDigit = {
                println("last digit")
            }
        )

        InkPrimaryButton(
            text = "Toggle Error",
            modifier = Modifier.fillMaxWidth()
        ) { error = error.not() }
    }
}
