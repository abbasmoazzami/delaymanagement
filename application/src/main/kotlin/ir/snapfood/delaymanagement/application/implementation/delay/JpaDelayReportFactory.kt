package ir.snapfood.delaymanagement.application.implementation.delay

import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportFactory
import org.springframework.stereotype.Service

@Service
class JpaDelayReportFactory : DelayReportFactory {

    override fun create(orderId: Int, delayTime: Int): DelayReport {
        return JpaDelayReport(
            orderId = orderId,
            delayTime = delayTime,
            status = DelayReport.Status.CREATED
        )
    }

}