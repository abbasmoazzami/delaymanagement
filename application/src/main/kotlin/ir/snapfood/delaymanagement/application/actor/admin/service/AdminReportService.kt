package ir.snapfood.delaymanagement.application.actor.admin.service

import ir.snapfood.delaymanagement.application.common.minusDays
import ir.snapfood.delaymanagement.application.common.startOfDay
import ir.snapfood.delaymanagement.application.implementation.delay.JpaDelayReportRepository
import org.springframework.stereotype.Service
import java.util.*


@Service
class AdminReportService(
    private val jpaDelayReportRepository: JpaDelayReportRepository
) {

    fun last7DaysDelaysByVendors(): List<ir.snapfood.delaymanagement.application.actor.admin.response.AggregatedVendorDelayTimeResponse> {
        val startDate = Date().minusDays(7).startOfDay()
        val endDate = Date()

        return jpaDelayReportRepository
            .findByCreatedAtBetweenAndAggregateByVendor(startDate, endDate)
    }

}

