package io.github.monolithic.invoicer.foundation.platform

import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisser
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisserDelegate

object AndroidSplashScreenDismisser : SplashScreenDismisser {
    private var delegate: SplashScreenDismisserDelegate? = null

    override fun attachDelegate(delegate: SplashScreenDismisserDelegate) {
        this.delegate = delegate
    }

    override fun detachDelegate() {
        this.delegate = null
    }

    override fun dismiss() {
        delegate?.dismissSplashScreen()
    }
}