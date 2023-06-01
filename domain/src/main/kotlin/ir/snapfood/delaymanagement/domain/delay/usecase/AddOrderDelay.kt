package ir.snapfood.delaymanagement.domain.delay.usecase

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportFactory
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportRepository
import ir.snapfood.delaymanagement.domain.delay.event.DelayReportCreated
import ir.snapfood.delaymanagement.domain.order.entity.Order
import ir.snapfood.delaymanagement.domain.order.usecase.GetOrderById
import ir.snapfood.delaymanagement.domain.trip.entity.Trip
import ir.snapfood.delaymanagement.domain.trip.usecase.ExistsAnyTrip
import ir.snapfood.delaymanagement.exception.InvalidDataException
import ir.snapfood.delaymanagement.service.timedelivery.TimeDeliveryService

class AddOrderDelay(
    private val timeDeliveryService: TimeDeliveryService,
    private val delayReportFactory: DelayReportFactory,
    private val delayReportRepository: DelayReportRepository,
    engine: Engine
) : UseCase<AddOrderDelay.Dto, Pair<DelayReport, Int?>>(engine) {

    data class Dto(val orderId: Int) : DTO

    override fun execute(input: Dto): Pair<DelayReport, Int?> {
        mustRunInTransaction()

        val order = call<Order>(GetOrderById.Dto(input.orderId))
        val currentOrderDelayTimeDelivery = order.takeIf { order -> order.hasDelay }?.currentDelayTime
            ?: throw InvalidDataException("orderTimeDeliveryWasNotExpired")
        checkOrderHasAnyActiveDelayReport(order.id)

        val (calculatedDelayTime, newTimeDeliveryEstimate) = if (orderHasAnyActiveTrip(order.id)) {
            val newEstimatedTimeDelivery = timeDeliveryService.estimate()
            Pair(currentOrderDelayTimeDelivery + newEstimatedTimeDelivery, newEstimatedTimeDelivery)
        } else {
            Pair(currentOrderDelayTimeDelivery, null)
        }

        newTimeDeliveryEstimate?.let(order::updateTimeDelivery)

        return delayReportFactory.create(order.id, calculatedDelayTime)
            .let(delayReportRepository::save)
            .also { delayReport -> registerEvent(DelayReportCreated(delayReport)) }
            .let { delayReport -> Pair(delayReport, newTimeDeliveryEstimate) }
    }

    private fun checkOrderHasAnyActiveDelayReport(orderId: Int) {
        ExistsAnyOrderDelay.Dto(orderId = orderId, statuses = DelayReport.Status.getActives())
            .takeIf { dto -> call(dto) } ?: return

        throw InvalidDataException("alreadyExistsActiveDelayReportForOrder")
    }

    private fun orderHasAnyActiveTrip(orderId: Int): Boolean {
        return ExistsAnyTrip.Dto(orderId = orderId, statuses = Trip.Status.getActives()).let(::call)
    }

}