package io.github.monolithic.invoicer.foundation.utils.events

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

data class Event<T>(
    val data: T
) {
    @OptIn(ExperimentalUuidApi::class)
    val id = Uuid.random().toString()
}

interface EventBus<T> {
    suspend fun publish(event: T)

    suspend fun subscribe(onEvent: (Event<T>) -> Unit)

}

class BaseEventBus<T> : EventBus<T> {
    private val _eventStack = MutableStateFlow(emptyList<Event<T>>())

    override suspend fun publish(event: T) {
        _eventStack.update {
            it + Event(event)
        }
    }

    private fun consume(event: Event<T>) {
        _eventStack.update { events ->
            events.filter { it.id != event.id }
        }
    }

    override suspend fun subscribe(onEvent: ((Event<T>)) -> Unit) {
        _eventStack.collectLatest {
            it.forEach { event ->
                onEvent(event)
                consume(event)
            }
        }
    }
}
