package io.github.monolithic.invoicer.foundation.platform

import com.google.firebase.Firebase
import com.google.firebase.analytics.analytics
import com.google.firebase.auth.auth
import io.github.monolithic.invoicer.foundation.platform.analytics.AnalyticsTracker
import io.github.monolithic.invoicer.foundation.platform.analytics.FirebaseAnalyticsTracker
import io.github.monolithic.invoicer.foundation.platform.firebaseAuth.AndroidFirebaseHelper
import io.github.monolithic.invoicer.foundation.platform.firebaseAuth.FirebaseHelper
import io.github.monolithic.invoicer.foundation.platform.firebaseAuth.GoogleFirebaseHelper
import io.github.monolithic.invoicer.foundation.platform.storage.AndroidLocalStorage
import io.github.monolithic.invoicer.foundation.platform.storage.LocalStorage
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
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

    factory<FirebaseHelper> {
        AndroidFirebaseHelper(
            firebaseAuth = Firebase.auth
        )
    }

    factory<GoogleFirebaseHelper> {
        GoogleFirebaseHelper(
            context = androidContext(),
            firebaseAuth = Firebase.auth
        )
    }
}
