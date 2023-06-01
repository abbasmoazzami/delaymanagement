package ir.snapfood.delaymanagement.application.service

import ir.snapfood.delaymanagement.common.abstracts.DomainEvent
import ir.snapfood.delaymanagement.service.event.EventService
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Service

@Service
class SpringEventService(
    private val applicationEventPublisher: ApplicationEventPublisher
) : EventService {

    override fun dispatch(event: DomainEvent) {
        applicationEventPublisher.publishEvent(event)
    }

}