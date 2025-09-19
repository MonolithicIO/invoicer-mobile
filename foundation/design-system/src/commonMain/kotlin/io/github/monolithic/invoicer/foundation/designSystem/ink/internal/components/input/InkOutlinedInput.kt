package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic.InkInputLabel
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic.InkInputLayout
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic.InkInputLayoutDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.basic.InkInputPlaceHolder
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputDefaults
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.modifier.textFieldBackground
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkOutlinedInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    isEnabled: Boolean = true,
    readOnly: Boolean = false,
    label: String? = null,
    placeholder: String? = null,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
    errorText: String? = null,
    isError: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    interactionSource: MutableInteractionSource? = null,
) {
    val baseTextStyle = InkTheme.typography.bodyXLarge.copy(fontWeight = FontWeight.SemiBold)
    val colors = InkInputDefaults.colors
    val internalInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val hasFocus by internalInteractionSource.collectIsFocusedAsState()

    val themedTextStyle by derivedStateOf {
        val textColor = when {
            !isEnabled -> colors.disabledText
            isError -> colors.errorText
            else -> colors.textColor
        }
        baseTextStyle.copy(color = textColor)
    }

    val cursorBrush by derivedStateOf {
        if (isError) SolidColor(colors.errorCursor)
        else SolidColor(colors.cursorColor)
    }

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
                .defaultMinSize(
                    minWidth = InkInputDefaults.MinWidth,
                    minHeight = InkInputDefaults.MinHeight
                ),
        onValueChange = onValueChange,
        enabled = isEnabled,
        readOnly = readOnly,
        textStyle = themedTextStyle,
        cursorBrush = cursorBrush,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        interactionSource = internalInteractionSource,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        decorationBox =
            @Composable { innerTextField ->
                val showPlaceHolder = placeholder != null && value.isEmpty() && !hasFocus

                InkInputLayout(
                    container = {
                        Box(
                            modifier = Modifier.layoutId(InkInputLayoutDefaults.ContainerId),
                            propagateMinConstraints = true
                        ) {
                            InkOutlinedInputContainer(
                                isError = isError,
                                isEnabled = isEnabled,
                                interactionSource = internalInteractionSource,
                                shape = InkTheme.shape.small,
                            )
                        }
                    },
                    isSingleLine = maxLines == 1,
                    paddingValues = PaddingValues(
                        vertical = InkTheme.spacing.xSmall,
                        horizontal = InkTheme.spacing.large
                    ),
                    leading = leadingContent,
                    trailing = trailingContent,
                    textField = innerTextField,
                    placeholder = if (showPlaceHolder) {
                        { InkInputPlaceHolder(text = placeholder, isError = isError) }
                    } else null,
                    label = if (label == null) null else {
                        {
                            InkInputLabel(
                                text = label,
                                isError = isError,
                                isEnabled = isEnabled
                            )
                        }
                    },
                )
            }
    )
}

@Composable
private fun InkOutlinedInputContainer(
    modifier: Modifier = Modifier,
    isError: Boolean,
    isEnabled: Boolean,
    interactionSource: InteractionSource,
    shape: Shape,
) {
    val hasFocus by interactionSource.collectIsFocusedAsState()
    val backgroundColor =
        InkInputDefaults.backgroundColor(
            isFocused = hasFocus,
            isEnabled = isEnabled
        )

    val borderColor by InkInputDefaults.borderColor(
        isError = isError,
        isFocused = hasFocus,
        isEnabled = isEnabled
    )

    Box(
        modifier = modifier
            .border(
                width = InkOutlinedInputDefaults.BorderWidth,
                color = borderColor,
                shape = shape
            )
            .textFieldBackground(
                color = backgroundColor::value,
                shape = shape
            )
    )
}


private object InkOutlinedInputDefaults {
    val BorderWidth = 2.dp
}
