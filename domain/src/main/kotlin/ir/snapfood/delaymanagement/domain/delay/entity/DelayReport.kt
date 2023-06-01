package ir.snapfood.delaymanagement.domain.delay.entity

import java.util.*

abstract class DelayReport {
    abstract val id: Int
    abstract val orderId: Int
    abstract var status: Status
    abstract var agentId: Int?
    abstract val delayTime: Int

    abstract val createdAt: Date
    abstract var updatedAt: Date

    enum class Status(val isActiveStatus: Boolean) {
        CREATED(true),
        ASSIGNED(true),
        RESOLVED(false);

        companion object {
            fun getActives() = values().filter(Status::isActiveStatus)
        }
    }

    fun assign(assignedAgentId: Int) {
        agentId = assignedAgentId
        status = Status.ASSIGNED
        updatedAt = Date()
    }
}