package ir.snapfood.delaymanagement.application.implementation.trip

import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.trip.entity.TripRepository
import ir.snapfood.delaymanagement.domain.trip.usecase.ExistsAnyTrip
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class TripUseCase(
    private val engine: UseCase.Engine,
    private val tripRepository: TripRepository
) {

    @Bean
    fun existsAnyTrip() = ExistsAnyTrip(
        engine = engine,
        tripRepository = tripRepository
    ).also(engine.useCaseGateway::register)

}