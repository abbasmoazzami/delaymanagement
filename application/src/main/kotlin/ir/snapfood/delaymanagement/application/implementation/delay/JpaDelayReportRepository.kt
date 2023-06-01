package ir.snapfood.delaymanagement.application.implementation.delay

import ir.snapfood.delaymanagement.domain.delay.entity.DelayReportRepository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface JpaDelayReportRepository : DelayReportRepository, JpaRepository<JpaDelayReport, Int> {

    //TODO rewrite with criteria
    @Query("select new ir.snapfood.delaymanagement.application.actor.admin.response.AggregatedVendorDelayTimeResponse(v.id, sum(dr.delayTime)) from JpaDelayReport dr inner join JpaOrder o on dr.order = o inner join JpaVendor v on o.vendor = v where dr.createdAt >= ?1 and dr.createdAt <= ?2 group by o.vendor order by sum(dr.delayTime) desc")
    fun findByCreatedAtBetweenAndAggregateByVendor(startDate: Date, endDate: Date): List<ir.snapfood.delaymanagement.application.actor.admin.response.AggregatedVendorDelayTimeResponse>

}