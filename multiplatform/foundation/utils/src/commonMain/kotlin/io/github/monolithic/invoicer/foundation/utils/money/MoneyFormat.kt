package io.github.monolithic.invoicer.foundation.utils.money

import kotlin.math.roundToInt

private const val MoneyMultiplier = 100.0
private const val CentsPadding = 2

fun Long.moneyFormat(
): String {
    val totalCents = this
    val dollars = (totalCents / MoneyMultiplier).roundToInt()
    val cents = (totalCents % MoneyMultiplier).roundToInt()

    val centsString = cents.toString().padStart(CentsPadding, '0')
    return "$$dollars.$centsString"
}
