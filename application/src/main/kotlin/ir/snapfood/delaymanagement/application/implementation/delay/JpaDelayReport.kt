package ir.snapfood.delaymanagement.application.implementation.delay

import ir.snapfood.delaymanagement.application.implementation.order.JpaOrder
import ir.snapfood.delaymanagement.domain.delay.entity.DelayReport
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "delay_reports")
class JpaDelayReport(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override val id: Int = 0,
    @Column(name = "order_id")
    override val orderId: Int = 0,
    @Enumerated(EnumType.STRING)
    override var status: Status = Status.CREATED,
    @Column(name = "agent_id")
    override var agentId: Int? = null,
    @Column(name = "delay_time")
    override val delayTime: Int = 0,

    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    override val createdAt: Date = Date(),
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    override var updatedAt: Date = Date()
) : DelayReport() {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", updatable = false, insertable = false)
    val order: JpaOrder? = null

}