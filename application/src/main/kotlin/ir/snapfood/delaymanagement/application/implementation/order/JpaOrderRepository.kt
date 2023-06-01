package ir.snapfood.delaymanagement.application.implementation.order

import ir.snapfood.delaymanagement.domain.order.entity.OrderRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaOrderRepository : OrderRepository, JpaRepository<JpaOrder, Int> {
    @Query("select max(id) from JpaOrder")
    fun getLatestId(): Int
}