package ir.snapfood.delaymanagement.application.actor.user.controller

import ir.snapfood.delaymanagement.application.actor.user.response.UserDelayMessageResponse
import ir.snapfood.delaymanagement.application.actor.user.service.UserOrderService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1.0/orders")
class UserOrderController(
    private val userOrderService: UserOrderService
) {

    @PostMapping("/{orderId}/delay")
    fun reportOrderDelay(@PathVariable orderId: Int): UserDelayMessageResponse {
        return userOrderService.addDelay(orderId)
    }

}