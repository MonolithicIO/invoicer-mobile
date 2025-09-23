package io.github.monolithic.invoicer.foundation.watchers.bus

import io.github.monolithic.invoicer.foundation.ui.events.EventAware
import io.github.monolithic.invoicer.foundation.ui.events.EventPublisher

class RefreshIntermediaryPublisher : EventAware<Unit> by EventPublisher()
