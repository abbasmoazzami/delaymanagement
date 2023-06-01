package ir.snapfood.delaymanagement.common.abstracts

import ir.snapfood.delaymanagement.service.event.EventService
import ir.snapfood.delaymanagement.service.gateway.UseCaseGateway
import ir.snapfood.delaymanagement.service.transaction.TransactionService
import java.util.concurrent.Executors

abstract class UseCase<in INPUT, out OUTPUT>(
    private val engine: Engine
) {

    abstract class Engine(
        val useCaseGateway: UseCaseGateway,
        val transactionService: TransactionService,
        val eventService: EventService,
    )

    private val pool = Executors.newFixedThreadPool(10)

    abstract fun execute(input: INPUT): OUTPUT

    fun executeTransactional(input: INPUT): OUTPUT {
        return engine.transactionService.doInTransaction { execute(input) }
    }

    fun registerEvent(event: DomainEvent) {
        engine.eventService.dispatch(event)
    }

    fun <OUTPUT> call(dto: DTO): OUTPUT {
        return engine.useCaseGateway.execute(dto)
    }

    fun <OUTPUT> callTransactional(dto: DTO): OUTPUT {
        return engine.useCaseGateway.executeTransactional(dto)
    }

    fun mustRunInTransaction() {
        require(engine.transactionService.isStarted()) {
            throw RuntimeException("transactionNotStarted")
        }
    }

}