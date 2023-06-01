package ir.snapfood.delaymanagement.application.implementation.trip

import ir.snapfood.delaymanagement.domain.trip.entity.Trip
import jakarta.persistence.*

@Entity
@Table(name = "trips")
data class JpaTrip(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,
    @Column(name = "order_id")
    override val orderId: Int = 0,
    @Enumerated(EnumType.STRING)
    override val status: Status = Status.ASSIGNED
) : Trip()