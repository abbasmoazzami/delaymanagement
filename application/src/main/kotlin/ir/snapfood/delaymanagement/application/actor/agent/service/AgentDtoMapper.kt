package ir.snapfood.delaymanagement.application.actor.agent.service

import ir.snapfood.delaymanagement.application.implementation.delay.JpaDelayReport
import ir.snapfood.delaymanagement.application.implementation.order.JpaOrder
import ir.snapfood.delaymanagement.application.implementation.vendor.JpaVendor
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import org.springframework.stereotype.Service

@Service
class AgentDtoMapper {

    fun toResponse(entity: DelayReport): ir.snapfood.delaymanagement.application.actor.agent.response.AgentDelayReportResponse {
        return (entity as JpaDelayReport)
            .let { jpaEntity ->
                ir.snapfood.delaymanagement.application.actor.agent.response.AgentDelayReportResponse(
                    id = jpaEntity.id,
                    order = toResponse(jpaEntity.order!!)
                )
            }
    }

    fun toResponse(entity: JpaOrder) = ir.snapfood.delaymanagement.application.actor.agent.response.AgentOrderResponse(
        id = entity.id,
        createdAt = entity.createdAt,
        vendor = toResponse(entity.vendor!!)
    )

    fun toResponse(entity: JpaVendor) =
        ir.snapfood.delaymanagement.application.actor.agent.response.AgentVendorResponse(
            id = entity.id
        )

}