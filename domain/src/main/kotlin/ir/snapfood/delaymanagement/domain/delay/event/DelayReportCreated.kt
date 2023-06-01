package ir.snapfood.delaymanagement.domain.delay.event

import ir.snapfood.delaymanagement.common.abstracts.DomainEvent
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport

data class DelayReportCreated(
    val delayReport: DelayReport
) : DomainEvent