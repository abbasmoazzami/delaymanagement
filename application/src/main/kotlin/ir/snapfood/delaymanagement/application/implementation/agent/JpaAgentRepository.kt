package ir.snapfood.delaymanagement.application.implementation.agent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaAgentRepository : JpaRepository<JpaAgent, Int> {
    @Query("select max(id) from JpaAgent")
    fun getLatestId(): Int
}