package io.github.monolithic.invoicer.foundation.utils.date

import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

fun LocalDate.defaultFormat(): String = "${this.dayOfMonth}/${this.monthNumber}/${this.year}"

fun Instant.defaultFormat(timeZone: TimeZone = TimeZone.currentSystemDefault()): String {
    return this.toLocalDateTime(timeZone).date.defaultFormat()
}
