package ir.snapfood.delaymanagement.domain.trip.usecase

import ir.snapfood.delaymanagement.common.abstracts.DTO
import ir.snapfood.delaymanagement.common.abstracts.UseCase
import ir.snapfood.delaymanagement.domain.trip.entity.Trip
import ir.snapfood.delaymanagement.domain.trip.entity.TripRepository

class ExistsAnyTrip(
    private val tripRepository: TripRepository,
    engine: Engine
) : UseCase<ExistsAnyTrip.Dto, Boolean>(engine) {

    data class Dto(
        val orderId: Int,
        val statuses: List<Trip.Status>
    ) : DTO

    override fun execute(input: Dto): Boolean {
        return tripRepository.existsByOrderIdAndStatusIn(input.orderId, input.statuses)
    }

}