package io.github.monolithic.invoicer.foundation.utils.money

private const val MoneyMultiplier = 100.0

fun Long.moneyFormat(
): String {
    val totalCents = this // Assuming 'this' already represents the amount in cents
    val dollars = totalCents / 100
    val cents = totalCents % 100
    // Ensure cents are always two digits (e.g., 5 cents is "05", 50 cents is "50")
    val centsString = cents.toString().padStart(2, '0')
    return "$$dollars.$centsString"
}
