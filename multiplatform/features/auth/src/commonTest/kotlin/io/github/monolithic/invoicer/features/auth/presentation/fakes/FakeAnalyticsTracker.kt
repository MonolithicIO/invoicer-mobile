package io.github.monolithic.invoicer.features.auth.presentation.fakes

import io.github.monolithic.invoicer.foundation.platform.analytics.AnalyticsEvent
import io.github.monolithic.invoicer.foundation.platform.analytics.AnalyticsTracker

class FakeAnalyticsTracker : AnalyticsTracker {

    var lastEvent: AnalyticsEvent? = null

    override fun track(event: AnalyticsEvent) {
        lastEvent = event
    }
}
