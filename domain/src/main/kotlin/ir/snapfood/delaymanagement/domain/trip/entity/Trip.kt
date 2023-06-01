package ir.snapfood.delaymanagement.domain.trip.entity

abstract class Trip {
    abstract val id: Int
    abstract val orderId: Int
    abstract val status: Status

    enum class Status(val isActiveStatus: Boolean) {
        ASSIGNED(true),
        AT_VENDOR(true),
        PICKED(true),
        DELIVERED(false);

        companion object {
            fun getActives() = values().filter(Status::isActiveStatus)
        }
    }

}