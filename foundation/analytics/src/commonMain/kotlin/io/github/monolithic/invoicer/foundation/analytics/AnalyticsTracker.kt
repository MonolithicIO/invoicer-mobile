package io.github.monolithic.invoicer.foundation.analytics

interface AnalyticsTracker {
    fun track(event: AnalyticsEvent)
}
