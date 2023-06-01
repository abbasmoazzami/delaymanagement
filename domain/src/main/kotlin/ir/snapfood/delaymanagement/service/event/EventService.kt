package ir.snapfood.delaymanagement.service.event

import ir.snapfood.delaymanagement.common.abstracts.DomainEvent

interface EventService {
    fun dispatch(event: DomainEvent)
}