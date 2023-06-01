package ir.snapfood.delaymanagement.application.common

import org.joda.time.DateTime
import org.springframework.context.MessageSource
import java.util.*

fun MessageSource.getMessageFa(message: String?, data: Array<Any>? = null): String? {
    message ?: return null

    return try {
        getMessage(message, data, Locale.forLanguageTag("fa"))
    } catch (e: Exception) {
        message
    }
}

fun Date.minusDays(days: Int) = DateTime(this).minusDays(days).toDate()
fun Date.startOfDay() = DateTime(this)
    .withHourOfDay(0)
    .withMinuteOfHour(0)
    .withSecondOfMinute(0)
    .withMillisOfSecond(0)
    .toDate()