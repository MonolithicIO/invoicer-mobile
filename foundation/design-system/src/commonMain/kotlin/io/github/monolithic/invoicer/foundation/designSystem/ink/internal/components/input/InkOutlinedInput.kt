package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputColors
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.modifier.defaultErrorSemantics
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
    leadingIcon: Painter? = null,
    trailingIcon: Painter? = null,
    supportingText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
    shape: Shape = InkOutlinedInputDefaults.shape,
) {
    val colors = InkOutlinedInputDefaults.colors

    BasicTextField(
        value = value,
        modifier =
            modifier
                .then(
                    if (label != null) {
                        Modifier.semantics(mergeDescendants = true) {}

                    } else {
                        Modifier
                    }
                )
                .defaultErrorSemantics(
                    isError = isError,
                    defaultErrorMessage = InkOutlinedInputDefaults.DEFAULT_ERROR_MESSAGE
                )
                .defaultMinSize(
                    minWidth = InkOutlinedInputDefaults.MinWidth,
                    minHeight = InkOutlinedInputDefaults.MinHeight
                ),
        onValueChange = onValueChange,
        enabled = enabled,
        readOnly = readOnly,
        textStyle = InkTheme.typography.bodyXLarge,
        cursorBrush = SolidColor(colors.cursorColor.value),
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = interactionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox =
            @Composable { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart,
                    modifier = Modifier
                        .padding(InkTheme.spacing.medium)
                        .border(
                            width = InkOutlinedInputDefaults.BorderWidth,
                            color = InkTheme.colorScheme.borderStroke.value
                        )
                ) {
                    innerTextField()
                }
            }
    )
}

internal object InkOutlinedInputDefaults {

    val BorderWidth = 2.dp
    val MinHeight = 65.dp
    val MinWidth = 280.dp
    const val DEFAULT_ERROR_MESSAGE = "Error"

    val shape: Shape
        @Composable
        get() {
            return InkTheme.shape.small
        }

    val colors: InkInputColors
        @Composable
        get() {
            return InkInputColors(
                textColor = InkTheme.colorScheme.onSurface,
                cursorColor = InkTheme.colorScheme.primary
            )
        }
}
