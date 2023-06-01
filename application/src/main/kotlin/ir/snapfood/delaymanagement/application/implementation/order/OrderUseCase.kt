package ir.snapfood.delaymanagement.application.implementation.order

import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.order.entity.OrderRepository
import ir.snapfood.delaymanagement.domain.order.usecase.GetOrderById
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class OrderUseCase(
    private val engine: UseCase.Engine,
    private val orderRepository: OrderRepository
) {

    @Bean
    fun getOrderById() = GetOrderById(
        orderRepository = orderRepository,
        engine = engine
    ).also(engine.useCaseGateway::register)

}