package ir.snapfood.delaymanagement.application.actor.agent.controller

import ir.snapfood.delaymanagement.application.actor.agent.response.AgentDelayReportResponse
import ir.snapfood.delaymanagement.application.actor.agent.service.AgentDelayReportService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/agent/api/v1.0/orders/delays")
class OrderDelayController(
    private val delayReportService: ir.snapfood.delaymanagement.application.actor.agent.service.AgentDelayReportService
) {

    @PostMapping("/assign")
    fun assignDelayReportToAgent(@RequestParam agentId: Int): ir.snapfood.delaymanagement.application.actor.agent.response.AgentDelayReportResponse {
        return delayReportService.assign(agentId)
    }

}