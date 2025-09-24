package io.github.monolithic.invoicer.foundation.platform

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import io.github.monolithic.invoicer.foundation.platform.analytics.AnalyticsTracker
import io.github.monolithic.invoicer.foundation.platform.analytics.FirebaseAnalyticsTracker
import io.github.monolithic.invoicer.foundation.platform.storage.AndroidLocalStorage
import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformNativeModule: Module = module {
    factory<AnalyticsTracker> {
        FirebaseAnalyticsTracker(
            tracker = Firebase.analytics
        )
    }

    factory<LocalStorage> {
        AndroidLocalStorage(
            context = androidApplication()
        )
    }
}
