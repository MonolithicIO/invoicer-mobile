package io.github.monolithic.invoicer.main

import androidx.lifecycle.ViewModel

// Can't store state on the activity due to state restoration. Delegating to vm
class MainViewModel : ViewModel() {
    var showSplashScreen = true
}