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

    val currentDelayTime: Int
        get() {
            val deadLineTimeDelivery = createdAt.addMinutes(timeDelivery)

            return if (deadLineTimeDelivery < Date()) {
                deadLineTimeDelivery.diffInMinutes(Date())
            } else 0
        }
    val hasDelay: Boolean get() = createdAt.addMinutes(timeDelivery) < Date()

    fun updateTimeDelivery(newEstimatedTimeDelivery: Int) {
        if (!hasDelay) {
            throw InvalidDataException("orderTimeDeliveryWasNotExpired")
        }

        timeDelivery = createdAt.diffInMinutes(Date()) + newEstimatedTimeDelivery
        updatedAt = Date()
    }

}