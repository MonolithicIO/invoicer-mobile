package io.github.monolithic.invoicer.foundation.utils.compose

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <T> FlowCollectEffect(
    flow: Flow<T>,
    key: Any?,
    onEvent: (T) -> Unit,
) {
    LaunchedEffect(key) {
        flow.collectLatest {
            onEvent(it)
        }
    }
}
