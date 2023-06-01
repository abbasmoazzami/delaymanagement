package ir.snapfood.delaymanagement.common.functions

import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.math.abs

fun Date.diffInMinutes(to: Date) = TimeUnit.MILLISECONDS.toMinutes(abs(to.time - time))
    .toInt()

fun Date.addMinutes(minutes: Int) = Date(time + (minutes * 60_000))
