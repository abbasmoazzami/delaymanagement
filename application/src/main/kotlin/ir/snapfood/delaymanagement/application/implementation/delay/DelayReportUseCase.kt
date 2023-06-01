package ir.snapfood.delaymanagement.application.implementation.delay

import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportRepository
import ir.snapfood.delaymanagement.domain.delay.usecase.AddOrderDelay
import ir.snapfood.delaymanagement.domain.delay.usecase.AssignDelayReportToAgent
import ir.snapfood.delaymanagement.domain.delay.usecase.ExistsAnyOrderDelay
import ir.snapfood.delaymanagement.service.timedelivery.TimeDeliveryService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DelayReportUseCase(
    private val engine: UseCase.Engine,
    private val delayReportRepository: DelayReportRepository,
    private val timeDeliveryService: TimeDeliveryService
) {

    @Bean
    fun addOrderDelay() = AddOrderDelay(
        engine = engine,
        delayReportRepository = delayReportRepository,
        delayReportFactory = JpaDelayReportFactory(),
        timeDeliveryService = timeDeliveryService
    ).also(engine.useCaseGateway::register)

    @Bean
    fun assignDelayOrderToAgent() = AssignDelayReportToAgent(
        engine = engine,
        delayReportRepository = delayReportRepository
    ).also(engine.useCaseGateway::register)

    @Bean
    fun existsAnyOrderDelay() = ExistsAnyOrderDelay(
        engine = engine,
        delayReportRepository = delayReportRepository
    ).also(engine.useCaseGateway::register)

}