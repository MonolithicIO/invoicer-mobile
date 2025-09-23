package io.github.monolithic.invoicer.foundation.utils.date

import kotlinx.datetime.LocalDate

fun LocalDate.defaultFormat(): String = "${this.dayOfMonth}/${this.monthNumber}/${this.year}"
