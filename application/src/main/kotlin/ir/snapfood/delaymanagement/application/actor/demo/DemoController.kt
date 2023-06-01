package ir.snapfood.delaymanagement.application.actor.demo

import ir.snapfood.delaymanagement.application.implementation.agent.JpaAgent
import ir.snapfood.delaymanagement.application.implementation.agent.JpaAgentRepository
import ir.snapfood.delaymanagement.application.implementation.delay.JpaDelayReport
import ir.snapfood.delaymanagement.application.implementation.delay.JpaDelayReportRepository
import ir.snapfood.delaymanagement.application.implementation.order.JpaOrder
import ir.snapfood.delaymanagement.application.implementation.order.JpaOrderRepository
import ir.snapfood.delaymanagement.application.implementation.trip.JpaTrip
import ir.snapfood.delaymanagement.application.implementation.trip.JpaTripRepository
import ir.snapfood.delaymanagement.application.implementation.vendor.JpaVendor
import ir.snapfood.delaymanagement.application.implementation.vendor.JpaVendorRepository
import ir.snapfood.delaymanagement.common.functions.addMinutes
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.trip.entity.Trip
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*
import kotlin.random.Random

@RestController
@RequestMapping("/demo/fill")
class DemoController(
    private val vendorRepository: JpaVendorRepository,
    private val agentRepository: JpaAgentRepository,
    private val orderRepository: JpaOrderRepository,
    private val tripRepository: JpaTripRepository,
    private val delayReportRepository: JpaDelayReportRepository
) {

    @GetMapping
    fun fillTables() {
        (0..50).map { JpaAgent() }
            .let(agentRepository::saveAll)
        (0..50).map { JpaVendor() }
            .let(vendorRepository::saveAll)

        val latestAgentId = agentRepository.getLatestId()
        val latestVendorId = vendorRepository.getLatestId()

        (0..50).map {
            JpaOrder(
                vendorId = Random.nextInt(latestVendorId) + 1,
                timeDelivery = Random.nextInt(60),
                createdAt = Date().addMinutes(Random.nextInt(10080) * arrayOf(-1, 1).random())
            )
        }.let(orderRepository::saveAll)

        val latestOrderId = orderRepository.getLatestId()
        (0..50).map {
            val agentId = Random.nextInt(latestAgentId)
                .let { if (it % 2 == 0) it + 1 else null }
            JpaDelayReport(
                orderId = Random.nextInt(latestOrderId) + 1,
                agentId = agentId,
                status = if (agentId == null) DelayReport.Status.CREATED else DelayReport.Status.RESOLVED,
                delayTime = Random.nextInt(60),
                createdAt = Date().addMinutes(Random.nextInt(10080) * arrayOf(-1, 1).random())
            )
        }.let(delayReportRepository::saveAll)

        (0..50).map { index ->
            JpaTrip(
                orderId = latestOrderId - index,
                status = Trip.Status.values().random()
            )
        }.let(tripRepository::saveAll)
    }

}