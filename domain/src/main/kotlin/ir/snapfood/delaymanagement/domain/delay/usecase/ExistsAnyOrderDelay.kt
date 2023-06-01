package ir.snapfood.delaymanagement.domain.delay.usecase

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportRepository

class ExistsAnyOrderDelay(
    private val delayReportRepository: DelayReportRepository,
    engine: Engine,
): UseCase<ExistsAnyOrderDelay.Dto, Boolean>(engine) {

    data class Dto(
        val agentId: Int? = null,
        val orderId: Int? = null,
        val statuses: List<DelayReport.Status>? = null
    ) : DTO

    override fun execute(input: Dto): Boolean {
        return delayReportRepository.existsByOrderIdAndAgentIdAndStatusIn(
            orderId = input.orderId,
            agentId = input.agentId,
            statuses = input.statuses
        )
    }

}