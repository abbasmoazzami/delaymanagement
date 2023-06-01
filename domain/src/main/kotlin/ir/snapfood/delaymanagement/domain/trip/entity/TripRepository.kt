package ir.snapfood.delaymanagement.domain.trip.entity

interface TripRepository {
    fun existsByOrderIdAndStatusIn(orderId: Int, status: List<Trip.Status>): Boolean
}