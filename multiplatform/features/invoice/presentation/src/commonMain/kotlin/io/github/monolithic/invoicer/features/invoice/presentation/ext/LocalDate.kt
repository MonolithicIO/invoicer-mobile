package io.github.monolithic.invoicer.features.invoice.presentation.ext

import kotlinx.datetime.LocalDate

@Suppress("MagicNumber")
internal fun String.toLocalDate(): LocalDate? {
    if (this.length != 8) return null

    val day = this.take(2)
        .toIntOrNull()
        ?.takeIf { it <= 31 }
        ?: return null

    val parsedMonth = this.drop(2).take(2)

    val month = parsedMonth
        .toIntOrNull()
        ?.takeIf { it in (1..12) }
        ?: return null

    val year = this.drop(4).take(4)
        .takeIf { it.length == 4 }
        ?.toIntOrNull()
        ?: return null

    return runCatching { LocalDate(year = year, monthNumber = month, dayOfMonth = day) }.fold(
        onSuccess = { it },
        onFailure = { null }
    )
}

internal fun LocalDate.toDateInputString(): String {
    val formatMonthNumber = monthNumber.toString().padStart(2, '0')
    val formatDayOfMonth = dayOfMonth.toString().padStart(2, '0')

    return "$formatDayOfMonth$formatMonthNumber$year"
}
