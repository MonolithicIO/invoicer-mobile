package io.github.monolithic.invoicer.foundation.watchers.bus

import io.github.monolithic.invoicer.foundation.utils.events.EventAware
import io.github.monolithic.invoicer.foundation.utils.events.EventPublisher

class NewInvoiceEventBus : EventAware<Unit> by EventPublisher()
