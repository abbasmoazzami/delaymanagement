package ir.snapfood.delaymanagement.application.service.timedelivery

import ir.snapfood.delaymanagement.application.service.timedelivery.remote.MockyApiClient
import ir.snapfood.delaymanagement.service.timedelivery.TimeDeliveryService
import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
class MockyTimeDeliveryService(
    private val mockyApiClient: MockyApiClient
) : TimeDeliveryService {

    override fun estimate(): Int {
        return runCatching {
            mockyApiClient.getNewTimeDelivery()
                .data.eta
        }.getOrDefault(Random.nextInt(10))
    }

}