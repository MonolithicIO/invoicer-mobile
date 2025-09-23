package io.github.monolithic.invoicer.foundation.platform.analytics

interface AnalyticsTracker {
    fun track(event: AnalyticsEvent)
}
