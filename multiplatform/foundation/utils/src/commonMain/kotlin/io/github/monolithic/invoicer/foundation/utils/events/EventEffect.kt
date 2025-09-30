package io.github.monolithic.invoicer.foundation.utils.events

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

@Composable
fun <T> EventEffect(
    publisher: EventBus<T>,
    onEvent: (T) -> Unit
) {
    LaunchedEffect(publisher) {
        publisher.subscribe { event ->
            onEvent(event.data)
        }
    }
}
