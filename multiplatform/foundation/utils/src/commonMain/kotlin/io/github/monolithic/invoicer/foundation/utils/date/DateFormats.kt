package io.github.monolithic.invoicer.foundation.utils.date

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDate.defaultFormat(): String {
    val day = dayOfMonth.toString().padStart(2, '0')
    val month = monthNumber.toString().padStart(2, '0')
    return "$month/$day/${this.year}"
}

fun Instant.toDateTimeString(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    val localDateTime = this.toLocalDateTime(timeZone)

    val datePart = localDateTime.date.defaultFormat() // Reuse your existing date formatting logic

    val hour = localDateTime.hour.toString().padStart(2, '0')
    val minute = localDateTime.minute.toString().padStart(2, '0')
    val second = localDateTime.second.toString().padStart(2, '0')
    val timePart = "$hour:$minute:$second"

    return "$datePart - $timePart"
}

fun Instant.defaultFormat(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    return this.toLocalDateTime(timeZone).date.defaultFormat()
}
