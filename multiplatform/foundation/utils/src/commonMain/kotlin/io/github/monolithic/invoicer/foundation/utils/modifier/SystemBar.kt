package io.github.monolithic.invoicer.foundation.utils.modifier

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.platform.LocalDensity

fun Modifier.systemBarBottomPadding(): Modifier {
    return composed {
        val systemBarPadding =
            WindowInsets.systemBars.asPaddingValues(LocalDensity.current)

        this.then(
            other = Modifier.padding(bottom = systemBarPadding.calculateBottomPadding())
        )
    }
}

fun Modifier.systemBarTopPadding(): Modifier {
    return composed {
        val systemBarPadding =
            WindowInsets.systemBars.asPaddingValues(LocalDensity.current)

        this.then(
            other = Modifier.padding(bottom = systemBarPadding.calculateTopPadding())
        )
    }
}
