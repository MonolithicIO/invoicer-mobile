package io.github.monolithic.invoicer.sharedApp.init.modules.mutes

import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisser
import io.github.monolithic.invoicer.foundation.platform.splashdismisser.SplashScreenDismisserDelegate

internal object MutedSplashScreenDismisser : SplashScreenDismisser {
    override fun attachDelegate(delegate: SplashScreenDismisserDelegate) = Unit

    override fun detachDelegate() = Unit
}
