package io.github.monolithic.invoicer.foundation.watchers.bus

import io.github.monolithic.invoicer.foundation.utils.events.EventBus
import io.github.monolithic.invoicer.foundation.utils.events.BaseEventBus

class RefreshIntermediaryPublisher : EventBus<Unit> by BaseEventBus()
