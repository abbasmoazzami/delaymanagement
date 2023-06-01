package ir.snapfood.delaymanagement.domain.delay.entity

interface DelayReportRepository {
    fun save(delayReport: DelayReport): DelayReport
    fun existsByOrderIdAndAgentIdAndStatusIn(orderId: Int?, agentId: Int?, statuses: List<DelayReport.Status>?): Boolean
    fun existsByAgentIdAndStatus(agentId: Int, status: DelayReport.Status): Boolean
    fun findFirstByStatusOrderByCreatedAtAsc(status: DelayReport.Status): DelayReport?
}