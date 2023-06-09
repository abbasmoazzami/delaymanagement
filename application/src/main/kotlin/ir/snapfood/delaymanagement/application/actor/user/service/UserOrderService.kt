package ir.snapfood.delaymanagement.application.actor.user.service

import ir.snapfood.delaymanagement.application.actor.user.response.UserDelayMessageResponse
import ir.snapfood.delaymanagement.application.common.getMessageFa
import ir.snapfood.delaymanagement.application.implementation.delay.JpaDelayReport
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.usecase.AddOrderDelay
import ir.snapfood.delaymanagement.service.gateway.UseCaseGateway
import org.springframework.context.MessageSource
import org.springframework.stereotype.Service

@Service
class UserOrderService(
    private val useCaseGateway: UseCaseGateway,
    private val messageSource: MessageSource
) {

    fun addDelay(orderId: Int): UserDelayMessageResponse {
        val (delayReport, newDeliveryEstimateTime) = useCaseGateway.executeTransactional<Pair<DelayReport, Int?>>(AddOrderDelay.Dto(orderId))
        val message = when (newDeliveryEstimateTime) {
            null -> messageSource.getMessageFa("willBeTrackedByAgents")
            else -> messageSource.getMessageFa("orderIsInDeliveryMode", arrayOf(newDeliveryEstimateTime))
        }

        return UserDelayMessageResponse(
            delayReportId = delayReport.id,
            message = message
        )
    }

}