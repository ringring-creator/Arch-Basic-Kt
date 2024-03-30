package com.ring.ring.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtil {
    fun currentLocalDate(): LocalDate = currentLocalDateTime().date

    private fun currentClock() = Clock.System.now()

    private fun currentLocalDateTime() = currentClock()
        .toLocalDateTime(TimeZone.currentSystemDefault())
}