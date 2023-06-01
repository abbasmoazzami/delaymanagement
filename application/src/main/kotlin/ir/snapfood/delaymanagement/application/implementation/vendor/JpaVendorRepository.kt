package ir.snapfood.delaymanagement.application.implementation.vendor

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface JpaVendorRepository : JpaRepository<JpaVendor, Int> {

    @Query("select max(id) from JpaVendor")
    fun getLatestId(): Int
}