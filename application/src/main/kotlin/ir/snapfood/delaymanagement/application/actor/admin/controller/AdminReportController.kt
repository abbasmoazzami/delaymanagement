package ir.snapfood.delaymanagement.application.actor.admin.controller

import ir.snapfood.delaymanagement.application.actor.admin.response.AggregatedVendorDelayTimeResponse
import ir.snapfood.delaymanagement.application.actor.admin.service.AdminReportService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/admin/api/v1.0/reports")
class AdminReportController(
    private val adminReportService: ir.snapfood.delaymanagement.application.actor.admin.service.AdminReportService
) {

    @GetMapping("/vendors/delays")
    fun reportVendorsDelays(): List<ir.snapfood.delaymanagement.application.actor.admin.response.AggregatedVendorDelayTimeResponse> {
        return adminReportService.last7DaysDelaysByVendors()
    }

}