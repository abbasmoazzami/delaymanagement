package ir.snapfood.delaymanagement.application.implementation.agent

import ir.snapfood.delaymanagement.domain.agent.Agent
import jakarta.persistence.*

@Entity
@Table(name = "agents")
data class JpaAgent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    override var id: Int = 0
) : Agent()