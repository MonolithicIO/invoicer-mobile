package io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkText
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.InkTextStyle
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.components.input.InkOtpInputDefaults.DigitBorderWidth
import io.github.monolithic.invoicer.foundation.designSystem.ink.internal.theme.InkTheme

@Composable
fun InkOtpInput(
    value: String,
    digitCount: Int,
    modifier: Modifier = Modifier,
    interactionSource: MutableInteractionSource? = null,
    isError: Boolean = false,
    onValueChange: (String) -> Unit,
    onFinalDigit: () -> Unit = {},
) {
    val colors = InkOtpInputDefaults.colors
    val internalInteractionSource = interactionSource ?: remember { MutableInteractionSource() }
    val hasFocus by internalInteractionSource.collectIsFocusedAsState()
    val focusManager = LocalFocusManager.current
    val keyboard = LocalSoftwareKeyboardController.current

    BasicTextField(
        modifier = modifier,
        value = TextFieldValue(text = value, selection = TextRange(value.length)),
        onValueChange = {
            if (it.text.length <= digitCount) {
                onValueChange(it.text)
                if (it.text.length == digitCount) {
                    onFinalDigit()
                }
            }
        },
        cursorBrush = SolidColor(colors.getTextColor(isError)),
        decorationBox = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(
                    space = InkTheme.spacing.small,
                    alignment = Alignment.CenterHorizontally
                )
            ) {
                repeat(digitCount) { digitIndex ->
                    OtpDigit(
                        modifier = Modifier
                            .weight(1f)
                            .widthIn(max = InkOtpInputDefaults.DigitWidth)
                            .aspectRatio(1f),
                        index = digitIndex,
                        text = value,
                        colors = colors,
                        isInputFocused = hasFocus,
                        digitCount = digitCount,
                        hasError = isError
                    )
                }
            }
        },
        singleLine = true,
        maxLines = 1,
        interactionSource = internalInteractionSource,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = {
                focusManager.clearFocus()
                keyboard?.hide()
            }
        )
    )
}

@Composable
private fun OtpDigit(
    index: Int,
    digitCount: Int,
    text: String,
    colors: InkOtpInputColors,
    isInputFocused: Boolean,
    hasError: Boolean,
    modifier: Modifier = Modifier
) {
    val character = remember(index, text) {
        if (index < text.length) {
            text[index].toString()
        } else {
            InkOtpInputDefaults.EmptyCharacter
        }
    }

    val isFocused = if (index == digitCount - 1) {
        isInputFocused && (text.length >= digitCount - 1)
    } else {
        (text.length == index) && isInputFocused
    }

    val showIndicator = isFocused && text.getOrNull(index) == null

    var digitHeight by remember {
        mutableStateOf(0.dp)
    }

    val density = LocalDensity.current

    Box(
        modifier = modifier
            .border(
                width = DigitBorderWidth,
                color = colors.borderColor(isFocused = isFocused, hasError = hasError).value,
                shape = InkTheme.shape.small
            )
            .background(
                color = InkTheme.colorScheme.surfaceLight,
                shape = InkTheme.shape.small
            )
            .onGloballyPositioned { coordinates ->
                digitHeight = with(density) { coordinates.size.height.toDp() }
            },
        contentAlignment = Alignment.Center,
    ) {
        InkText(
            text = character,
            color = colors.getTextColor(hasError),
            textAlign = TextAlign.Center,
            style = InkTextStyle.Heading4,
            weight = FontWeight.Bold
        )

        if (showIndicator) {
            OtpIndicator(
                modifier = Modifier,
                height = digitHeight.div(InkOtpInputDefaults.IndicatorHeightMutiplier),
                color = colors.getTextColor(hasError = hasError),
                index = index
            )
        }
    }
}

@Composable
private fun BoxScope.OtpIndicator(
    index: Int,
    height: Dp,
    color: Color,
    modifier: Modifier = Modifier
) {
    val transition = rememberInfiniteTransition(label = "Indicator-$index")

    val alpha by transition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = InkOtpInputDefaults.IndicatorAnimationDuration),
            repeatMode = RepeatMode.Reverse
        )
    )

    Box(
        modifier = modifier
            .align(Alignment.Center)
            .alpha(alpha)
            .width(InkOtpInputDefaults.IndicatorWidth)
            .height(height)
            .background(color)
    )
}

internal object InkOtpInputDefaults {
    val DigitWidth = 66.dp
    val DigitBorderWidth = 2.dp
    val IndicatorWidth = 2.dp
    const val IndicatorHeightMutiplier = 2
    const val EmptyCharacter = ""
    const val IndicatorAnimationDuration = 500

    val colors: InkOtpInputColors
        @Composable
        get() = InkOtpInputColors(
            border = InkTheme.colorScheme.borderStroke,
            focusedBorder = InkTheme.colorScheme.primaryVariant,
            textColor = InkTheme.colorScheme.onSurface,
            errorBorder = InkTheme.colorScheme.error,
            errorText = InkTheme.colorScheme.error
        )
}

internal data class InkOtpInputColors(
    private val border: Color,
    private val focusedBorder: Color,
    private val errorBorder: Color,
    private val errorText: Color,
    private val textColor: Color,
) {
    @Composable
    fun borderColor(isFocused: Boolean, hasError: Boolean) = animateColorAsState(
        targetValue = when {
            hasError && isFocused -> errorBorder.copy(alpha = 0.5f)
            hasError -> errorBorder
            isFocused -> focusedBorder
            else -> border
        },
    )

    @Composable
    fun getTextColor(hasError: Boolean) = if (hasError) errorText else textColor
}
