package ir.snapfood.delaymanagement.domain.delay.entity

interface DelayReportFactory {
    fun create(orderId: Int, delayTime: Int): DelayReport
}