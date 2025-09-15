package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkOutlinedInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingContent: @Composable (() -> Unit)? = null,
    trailingContent: @Composable (() -> Unit)? = null,
    supportingText: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
) {
    val colors = InkOutlinedInputDefaults.colors

    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = InkTheme.shape.small,
        label = if (label != null) {
            {
                InkText(
                    text = label,
                    style = InkTextStyle.BodyXlarge,
                    weight = FontWeight.SemiBold
                )
            }
        } else null,
        interactionSource = interactionSource,
        textStyle = InkTheme.typography.bodyXLarge.copy(fontWeight = FontWeight.Medium),
        trailingIcon = trailingContent,
        leadingIcon = leadingContent,
        supportingText = supportingText,
        enabled = enabled,
        readOnly = readOnly,
        placeholder = if (placeholder != null) {
            {
                InkText(
                    text = placeholder,
                    style = InkTextStyle.BodyXlarge,
                    weight = FontWeight.Normal,
                    color = InkTheme.colorScheme.disabled
                )
            }
        } else null,
        isError = isError,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        colors = colors,
    )
}


private object InkOutlinedInputDefaults {

    val colors: TextFieldColors
        @Composable
        get() {
            return TextFieldColors(
                focusedTextColor = InkTheme.colorScheme.onSurfaceVariant.value,
                unfocusedTextColor = InkTheme.colorScheme.onSurfaceVariant.value,
                disabledTextColor = InkTheme.colorScheme.onDisabled.value,
                focusedContainerColor = InkTheme.colorScheme.textSurface.value,
                unfocusedContainerColor = InkTheme.colorScheme.textSurfaceLight.value,
                disabledContainerColor = InkTheme.colorScheme.disabled.value,
                errorContainerColor = InkTheme.colorScheme.errorBackground.value,
                cursorColor = InkTheme.colorScheme.onSurfaceVariant.value,
                errorCursorColor = InkTheme.colorScheme.error.value,
                textSelectionColors = TextSelectionColors(
                    handleColor = InkTheme.colorScheme.primary.value,
                    backgroundColor = InkTheme.colorScheme.primary.value.copy(alpha = 0.4f)
                ),
                focusedIndicatorColor = InkTheme.colorScheme.borderStroke.value,
                unfocusedIndicatorColor = InkTheme.colorScheme.textSurfaceLight.value,
                disabledIndicatorColor = InkTheme.colorScheme.disabled.value,
                errorIndicatorColor = InkTheme.colorScheme.error.value,
                focusedLeadingIconColor = InkTheme.colorScheme.none.value,
                unfocusedLeadingIconColor = InkTheme.colorScheme.none.value,
                disabledLeadingIconColor = InkTheme.colorScheme.none.value,
                errorLeadingIconColor = InkTheme.colorScheme.none.value,
                focusedTrailingIconColor = InkTheme.colorScheme.none.value,
                unfocusedTrailingIconColor = InkTheme.colorScheme.none.value,
                disabledTrailingIconColor = InkTheme.colorScheme.none.value,
                errorTrailingIconColor = InkTheme.colorScheme.none.value,
                focusedLabelColor = InkTheme.colorScheme.onBackground.value,
                unfocusedLabelColor = InkTheme.colorScheme.onBackground.value,
                disabledLabelColor = InkTheme.colorScheme.onBackground.value,
                errorLabelColor = InkTheme.colorScheme.error.value,
                focusedPlaceholderColor = InkTheme.colorScheme.disabled.value,
                unfocusedPlaceholderColor = InkTheme.colorScheme.disabled.value,
                disabledPlaceholderColor = InkTheme.colorScheme.disabled.value,
                errorPlaceholderColor = InkTheme.colorScheme.error.value,
                focusedSupportingTextColor = InkTheme.colorScheme.disabled.value,
                unfocusedSupportingTextColor = InkTheme.colorScheme.disabled.value,
                disabledSupportingTextColor = InkTheme.colorScheme.disabled.value,
                errorSupportingTextColor = InkTheme.colorScheme.error.value,
                focusedPrefixColor = InkTheme.colorScheme.none.value,
                unfocusedPrefixColor = InkTheme.colorScheme.none.value,
                disabledPrefixColor = InkTheme.colorScheme.none.value,
                errorPrefixColor = InkTheme.colorScheme.none.value,
                focusedSuffixColor = InkTheme.colorScheme.none.value,
                unfocusedSuffixColor = InkTheme.colorScheme.none.value,
                disabledSuffixColor = InkTheme.colorScheme.none.value,
                errorSuffixColor = InkTheme.colorScheme.none.value,
                errorTextColor = InkTheme.colorScheme.error.value
            )
        }


}