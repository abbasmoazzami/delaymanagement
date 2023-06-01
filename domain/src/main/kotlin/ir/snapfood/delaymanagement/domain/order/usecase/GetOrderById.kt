package ir.snapfood.delaymanagement.domain.order.usecase

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.order.entity.Order
import ir.snapfood.delaymanagement.domain.order.entity.OrderRepository

class GetOrderById(
    private val orderRepository: OrderRepository,
    engine: Engine
) : UseCase<GetOrderById.Dto, Order>(engine) {

    data class Dto(val orderId: Int) : DTO

    override fun execute(input: Dto): Order {
        return orderRepository.getById(input.orderId)
    }

}