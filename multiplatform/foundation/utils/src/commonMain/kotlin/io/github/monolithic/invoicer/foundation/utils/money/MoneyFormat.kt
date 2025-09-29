package io.github.monolithic.invoicer.foundation.utils.money

private const val MoneyMultiplier = 100.0
private const val CentsPadding = 2

fun Long.moneyFormat(
): String {
    val totalCents = this
    val dollars = totalCents / MoneyMultiplier
    val cents = totalCents % MoneyMultiplier

    val centsString = cents.toString().padStart(CentsPadding, '0')
    return "$$dollars.$centsString"
}
