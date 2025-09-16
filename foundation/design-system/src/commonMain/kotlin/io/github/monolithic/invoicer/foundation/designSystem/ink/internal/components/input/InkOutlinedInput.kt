package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.InteractionSource
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.SpacerSize
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.VerticalSpacer
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.props.InkInputColors
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.modifier.defaultErrorSemantics
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
) {
    val baseTextStyle = InkTheme.typography.bodyXLarge.copy(fontWeight = FontWeight.SemiBold)
    val colors = InkOutlinedInputDefaults.colors
    val internalInteractionSource = interactionSource ?: remember { MutableInteractionSource() }

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
                .defaultErrorSemantics(
                    isError = isError,
                    defaultErrorMessage = InkOutlinedInputDefaults.DEFAULT_ERROR_MESSAGE
                )
                .defaultMinSize(
                    minWidth = InkOutlinedInputDefaults.MinWidth,
                    minHeight = InkOutlinedInputDefaults.MinHeight
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
                InkOutlinedInputContainer(
                    interactionSource = internalInteractionSource,
                    isError = isError,
                    isEnabled = isEnabled,
                    shape = InkTheme.shape.small,
                    label = label
                ) {
                    if (value.isBlank() && placeholder != null) {
                        Placeholder(
                            text = placeholder,
                            isError = isError
                        )
                    } else {
                        innerTextField()
                    }
                }
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
    label: String?,
    textFieldSlot: @Composable () -> Unit
) {
    val hasFocus by interactionSource.collectIsFocusedAsState()

    val backgroundColor =
        InkOutlinedInputDefaults.backgroundColor(
            isFocused = hasFocus,
            isEnabled = isEnabled
        )

    val borderColor by InkOutlinedInputDefaults.borderColor(
        isError = isError,
        isFocused = hasFocus,
        isEnabled = isEnabled
    )

    val labelColor by InkOutlinedInputDefaults.labelColor(isError = isError, isEnabled = isEnabled)

    Column(
        modifier = modifier.fillMaxWidth()

    ) {
        label?.let {
            InkText(
                text = label,
                style = InkTextStyle.BodyXlarge,
                weight = FontWeight.SemiBold,
                color = labelColor,
                maxLines = 1
            )
            VerticalSpacer(SpacerSize.XSmall)
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = InkOutlinedInputDefaults.BorderWidth,
                    color = borderColor,
                    shape = shape
                )
                .textFieldBackground(
                    color = backgroundColor::value,
                    shape = shape
                )
                .padding(InkTheme.spacing.medium),
            contentAlignment = Alignment.CenterStart
        ) {
            textFieldSlot()
        }
    }
}

@Composable
private fun Placeholder(
    isError: Boolean,
    text: String,
    modifier: Modifier = Modifier
) {
    InkText(
        text = text,
        modifier = modifier,
        style = InkTextStyle.BodyMedium,
        color = InkOutlinedInputDefaults.placeholderColor(isError = isError),
        maxLines = 1
    )
}

internal object InkOutlinedInputDefaults {

    val BorderWidth = 2.dp
    val MinHeight = 65.dp
    val MinWidth = 280.dp
    const val DEFAULT_ERROR_MESSAGE = "Error"
    const val TextFieldAnimationDuration = 150

    val shape: Shape
        @Composable
        get() {
            return InkTheme.shape.small
        }

    val colors: InkInputColors
        @Composable
        get() {
            return InkInputColors(
                textColor = InkTheme.colorScheme.onSurfaceVariant,
                cursorColor = InkTheme.colorScheme.onSurfaceVariant,
                focusedBackground = InkTheme.colorScheme.surfaceDark,
                focusedIndicator = InkTheme.colorScheme.surfaceDark,
                unfocusedBackground = InkTheme.colorScheme.surfaceLight,
                unfocusedIndicator = InkTheme.colorScheme.surfaceLight,
                disabledIndicator = InkTheme.colorScheme.disabled,
                disabledBackground = InkTheme.colorScheme.disabled,
                disabledText = InkTheme.colorScheme.onDisabled,
                disabledLabel = InkTheme.colorScheme.onDisabled,
                errorText = InkTheme.colorScheme.error,
                errorIndicator = InkTheme.colorScheme.error,
                errorLabel = InkTheme.colorScheme.error,
                errorCursor = InkTheme.colorScheme.error,
                errorPlaceholder = InkTheme.colorScheme.error,
                placeholderColor = InkTheme.colorScheme.onDisabled,
                labelColor = InkTheme.colorScheme.onSurfaceVariant,
            )
        }

    @Composable
    fun backgroundColor(isFocused: Boolean, isEnabled: Boolean): State<Color> {
        val colors = colors
        val newColor = when {
            !isEnabled -> colors.disabledBackground
            isFocused -> colors.focusedBackground
            else -> colors.unfocusedBackground
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun borderColor(isError: Boolean, isFocused: Boolean, isEnabled: Boolean): State<Color> {
        val colors = colors
        val newColor = when {
            !isEnabled -> colors.disabledIndicator
            isError -> colors.errorIndicator
            isFocused -> colors.focusedIndicator
            else -> colors.unfocusedIndicator
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun labelColor(isError: Boolean, isEnabled: Boolean): State<Color> {
        val colors = colors
        val newColor = when {
            !isEnabled -> colors.disabledLabel
            isError -> colors.errorLabel
            else -> colors.labelColor
        }

        return animateColorAsState(
            targetValue = newColor,
            animationSpec = tween(durationMillis = TextFieldAnimationDuration)
        )
    }

    @Composable
    fun placeholderColor(isError: Boolean): Color {
        return if (isError) colors.errorPlaceholder else colors.placeholderColor
    }
}
