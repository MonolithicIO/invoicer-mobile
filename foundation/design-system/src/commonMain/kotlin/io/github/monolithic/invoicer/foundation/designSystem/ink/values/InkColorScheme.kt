package io.github.monolithic.invoicer.foundation.designSystem.ink.values

import androidx.compose.ui.graphics.Color
import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.InkColors


data class InkColorScheme(
    val background: Color
)

internal fun lightInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.BlackWhite.white
    )
}

internal fun darkInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.Dark.dark1
    )
}