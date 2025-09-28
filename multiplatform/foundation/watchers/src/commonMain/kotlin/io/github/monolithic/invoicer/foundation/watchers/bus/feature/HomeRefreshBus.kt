package io.github.monolithic.invoicer.foundation.watchers.bus.feature

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

interface HomeRefreshBus {
    suspend fun publishEvent(event: Unit)
    suspend fun subscribe(): Flow<Unit>
}

internal object HomeRefreshBusImpl : HomeRefreshBus {

    // Setting buffer and replay just in case to avoid dead messages
    private val _events = MutableSharedFlow<Unit>(
        replay = 10,
        extraBufferCapacity = 5
    )

    override suspend fun publishEvent(event: Unit) {
        _events.emit(event)
    }

    override suspend fun subscribe(): Flow<Unit> = _events.asSharedFlow()
}
