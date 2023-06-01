package ir.snapfood.delaymanagement.application.actor.agent.response

data class AgentDelayReportResponse(
    val id: Int,
    val order: ir.snapfood.delaymanagement.application.actor.agent.response.AgentOrderResponse,
)