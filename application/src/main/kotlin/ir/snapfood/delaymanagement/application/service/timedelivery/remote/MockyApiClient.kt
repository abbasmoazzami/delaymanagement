package ir.snapfood.delaymanagement.application.service.timedelivery.remote

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "mocky", url = "https://run.mocky.io/v3", value = "mocky")
interface MockyApiClient {

    @GetMapping("/122c2796-5df4-461c-ab75-87c1192b17f7")
    fun getNewTimeDelivery(): MockyApiResponse

}