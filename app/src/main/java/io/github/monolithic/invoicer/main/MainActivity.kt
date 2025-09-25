package io.github.monolithic.invoicer.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisser
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisserDelegate
import io.github.monolithic.invoicer.sharedApp.InvoicerApp
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity(), SplashScreenDismisserDelegate {
    private val splashScreenDismisser by inject<SplashScreenDismisser>()

    private var showSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenDismisser.attachDelegate(this)
        // This causes the app to freeze on screen rotation: find a fix
        installSplashScreen().setKeepOnScreenCondition {
            showSplashScreen
        }
        setContent {
            InvoicerApp()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        splashScreenDismisser.detachDelegate()
    }

    override fun dismissSplashScreen() {
        showSplashScreen = false
    }
}
