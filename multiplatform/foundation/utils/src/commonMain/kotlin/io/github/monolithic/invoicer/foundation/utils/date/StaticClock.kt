package io.github.monolithic.invoicer.foundation.utils.date

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

object StaticClock: Clock {
    override fun now(): Instant {
        return Instant.parse("2023-01-01T00:00:00Z")
    }
}