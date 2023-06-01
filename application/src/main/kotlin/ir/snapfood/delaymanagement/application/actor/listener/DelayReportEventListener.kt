package ir.snapfood.delaymanagement.application.actor.listener

import ir.snapfood.delaymanagement.domain.delay.event.DelayReportCreated
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionalEventListener

@Component
class DelayReportEventListener {

    @TransactionalEventListener
    fun userCreated(event: DelayReportCreated) {

    }

}