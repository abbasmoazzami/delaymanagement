package ir.snapfood.delaymanagement.domain.order.entity

interface OrderRepository {
    fun save(order: Order): Order
    fun getById(id: Int): Order
}