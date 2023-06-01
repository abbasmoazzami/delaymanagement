package ir.snapfood.delaymanagement.application.actor.agent.service

import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.usecase.AssignDelayReportToAgent
import ir.snapfood.delaymanagement.service.gateway.UseCaseGateway
import org.springframework.stereotype.Service

@Service
class AgentDelayReportService(
    private val useCaseGateway: UseCaseGateway,
    private val agentDtoMapper: ir.snapfood.delaymanagement.application.actor.agent.service.AgentDtoMapper
) {

    fun assign(agentId: Int): ir.snapfood.delaymanagement.application.actor.agent.response.AgentDelayReportResponse {
        return useCaseGateway.executeTransactional<DelayReport>(AssignDelayReportToAgent.Dto(agentId))
            .let(agentDtoMapper::toResponse)
    }

}