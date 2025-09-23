package io.github.monolithic.invoicer.features.auth.presentation.screens.signup

import io.github.monolithic.invoicer.foundation.analytics.AnalyticsEvent

internal object SignUpAnalytics {

    data object Started : AnalyticsEvent {
        override val name: String = "signup_started"
        override val params: Map<String, String> = mapOf()
    }

    data object Failure : AnalyticsEvent {
        override val name: String = "signup_failure"
        override val params: Map<String, String> = mapOf()
    }

    data object Success : AnalyticsEvent {
        override val name: String = "signup_success"
        override val params: Map<String, String> = mapOf()
    }
}
