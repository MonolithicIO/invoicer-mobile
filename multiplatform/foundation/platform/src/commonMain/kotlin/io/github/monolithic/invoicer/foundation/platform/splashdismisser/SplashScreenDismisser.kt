package io.github.monolithic.invoicer.foundation.platform.splashdismisser

interface SplashScreenDismisser {
    fun attachDelegate(delegate: SplashScreenDismisserDelegate)
    fun detachDelegate()
    fun dismiss()
}

interface SplashScreenDismisserDelegate {
    fun dismissSplashScreen()
}