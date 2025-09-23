package io.github.monolithic.invoicer.foundation.analytics.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import io.github.monolithic.invoicer.foundation.analytics.AnalyticsTracker
import io.github.monolithic.invoicer.foundation.analytics.impl.FirebaseAnalyticsTracker
import org.koin.core.module.Module
import org.koin.dsl.module

actual val analyticsPlatformModule: Module = module {
    factory<AnalyticsTracker> {
        FirebaseAnalyticsTracker(
            tracker = Firebase.analytics
        )
    }
}
