package io.github.monolithic.invoicer.foundation.utils.date

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

@OptIn(ExperimentalMaterial3Api::class)
class BlockPastSelectableDate(
    private val minDateInMillis: Long,
    private val timeZone: TimeZone
) : SelectableDates {

    override fun isSelectableDate(utcTimeMillis: Long): Boolean {
        return utcTimeMillis >= minDateInMillis
    }

    override fun isSelectableYear(year: Int): Boolean {
        val currentDate = Instant
            .fromEpochMilliseconds(minDateInMillis)
            .toLocalDateTime(timeZone)
        return year >= currentDate.year
    }
}
