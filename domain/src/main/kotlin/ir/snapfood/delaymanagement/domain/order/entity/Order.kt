package ir.snapfood.delaymanagement.domain.order.entity

import ir.snapfood.delaymanagement.common.functions.addMinutes
import ir.snapfood.delaymanagement.common.functions.diffInMinutes
import ir.snapfood.delaymanagement.exception.InvalidDataException
import java.util.*

abstract class Order {
    abstract val id: Int
    abstract val vendorId: Int
    abstract var timeDelivery: Int
    abstract val createdAt: Date
    abstract var updatedAt: Date

    private val deadLineTimeDelivery: Date get() = createdAt.addMinutes(timeDelivery)
    val currentDelayTime: Int
        get() {
            val deadLineTimeDelivery = createdAt.addMinutes(timeDelivery)

            return if (deadLineTimeDelivery < Date()) {
                deadLineTimeDelivery.diffInMinutes(Date())
            } else 0
        }
    val deliveryTimer: Int
        get() = createdAt.addMinutes(timeDelivery)
            .diffInMinutes(Date())

    fun updateTimeDelivery(newEstimatedTimeDelivery: Int) {
        if (currentDelayTime == 0) {
            throw InvalidDataException("orderTimeDeliveryWasNotExpired")
        }

        timeDelivery = createdAt.diffInMinutes(Date()) + newEstimatedTimeDelivery
        updatedAt = Date()
    }

}