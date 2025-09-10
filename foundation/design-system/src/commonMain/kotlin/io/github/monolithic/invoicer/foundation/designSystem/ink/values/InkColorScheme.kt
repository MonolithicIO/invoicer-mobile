package io.github.monolithic.invoicer.foundation.designSystem.ink.values

import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.InkColor
import io.github.monolithic.invoicer.foundation.designSystem.ink.tokens.InkColors


data class InkColorScheme(
    val background: InkColor,
    val onBackground: InkColor,
)

internal fun lightInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.BlackWhite.white,
        onBackground = InkColors.GreyScale.grey900
    )
}

internal fun darkInkColorScheme(): InkColorScheme {
    return InkColorScheme(
        background = InkColors.Dark.dark1,
        onBackground = InkColors.BlackWhite.white
    )
}