package ir.snapfood.delaymanagement.application.actor.admin.response

data class AggregatedVendorDelayTimeResponse(
    val vendorId: Int = 0,
    val totalDelayTime: Long = 0
)
