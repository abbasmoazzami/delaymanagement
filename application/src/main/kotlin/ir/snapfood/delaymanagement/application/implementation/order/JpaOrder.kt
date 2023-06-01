package ir.snapfood.delaymanagement.application.implementation.order

import ir.snapfood.delaymanagement.application.implementation.vendor.JpaVendor
import ir.snapfood.delaymanagement.domain.order.entity.Order
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "orders")
data class JpaOrder(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,
    @Column(name = "vendor_id")
    override val vendorId: Int = 0,

    @Column(name = "time_delivery")
    override var timeDelivery: Int = 0,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at")
    override val createdAt: Date = Date(),
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_at")
    override var updatedAt: Date = Date()
) : Order() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id", updatable = false, insertable = false)
    val vendor: JpaVendor? = null

}