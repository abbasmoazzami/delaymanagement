package ir.snapfood.delaymanagement.application.service.timedelivery.remote

data class MockyApiResponse(
    val status: String,
    val data: NestedResponse
) {
    data class NestedResponse(val eta: Int)
}