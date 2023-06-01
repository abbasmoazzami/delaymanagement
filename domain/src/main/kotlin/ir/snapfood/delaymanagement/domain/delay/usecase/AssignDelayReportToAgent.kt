package ir.snapfood.delaymanagement.domain.delay.usecase

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportRepository
import ir.snapfood.delaymanagement.exception.InvalidDataException

class AssignDelayReportToAgent(
    private val delayReportRepository: DelayReportRepository,
    engine: Engine
) : UseCase<AssignDelayReportToAgent.Dto, DelayReport?>(engine) {

    data class Dto(val agentId: Int) : DTO

    override fun execute(input: Dto): DelayReport? {
        mustRunInTransaction()

        require(delayReportRepository.existsByAgentIdAndStatus(input.agentId, DelayReport.Status.ASSIGNED).not()) {
            throw InvalidDataException("agentHasAnActiveAssignment", arrayOf(input.agentId))
        }

        return delayReportRepository.findFirstByStatusOrderByCreatedAtAsc(DelayReport.Status.CREATED)
            ?.apply { assign(input.agentId) }
    }

}