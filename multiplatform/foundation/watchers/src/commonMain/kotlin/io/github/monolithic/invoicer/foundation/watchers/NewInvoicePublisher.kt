package io.github.monolithic.invoicer.foundation.watchers

import io.github.monolithic.invoicer.foundation.ui.events.EventAware
import io.github.monolithic.invoicer.foundation.ui.events.EventPublisher

class NewInvoicePublisher : EventAware<Unit> by EventPublisher()
