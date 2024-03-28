package com.ring.ring.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateUtil {
    fun currentLocalDate(): LocalDate = Clock.System.now()
        .toLocalDateTime(
            TimeZone.currentSystemDefault()
        ).date
}