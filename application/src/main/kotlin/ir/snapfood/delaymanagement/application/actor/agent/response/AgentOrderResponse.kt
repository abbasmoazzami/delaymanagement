package ir.snapfood.delaymanagement.application.actor.agent.response

import java.util.*

data class AgentOrderResponse(
    val id: Int,
    val createdAt: Date,
    val vendor: ir.snapfood.delaymanagement.application.actor.agent.response.AgentVendorResponse
)