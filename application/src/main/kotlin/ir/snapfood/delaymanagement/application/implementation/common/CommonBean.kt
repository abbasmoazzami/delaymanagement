package ir.snapfood.delaymanagement.application.implementation.common

import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.service.event.EventService
import ir.snapfood.delaymanagement.service.gateway.UseCaseGateway
import ir.snapfood.delaymanagement.service.transaction.TransactionService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CommonBean(
    private val transactionService: TransactionService,
    private val eventService: EventService
) {

    @Bean
    fun useCaseGateway() = UseCaseGateway()

    @Bean
    fun useCaseEngin(useCaseGateway: UseCaseGateway) = SpringUseCaseEngin(
        transactionService = transactionService,
        eventService = eventService,
        useCaseGateway = useCaseGateway
    )

    class SpringUseCaseEngin(
        transactionService: TransactionService,
        eventService: EventService,
        useCaseGateway: UseCaseGateway
    ) : UseCase.Engine(
        transactionService = transactionService,
        eventService = eventService,
        useCaseGateway = useCaseGateway
    )
}