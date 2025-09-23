package io.github.monolithic.invoicer.foundation.utils.money

private const val MoneyMultiplier = 100.0

fun Long.moneyFormat(
): String {
    return "$${(this / MoneyMultiplier) * MoneyMultiplier}"
}
