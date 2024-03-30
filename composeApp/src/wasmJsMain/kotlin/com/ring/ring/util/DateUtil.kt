package com.ring.ring.util

import kotlinx.datetime.*

object DateUtil {
    fun currentEpochMilliseconds() = Clock.System.now().toEpochMilliseconds()

    fun toLocalDate(epochMilliseconds: Long): LocalDate {
        val localDateTime = toLocalDateTime(epochMilliseconds)
        return localDateTime.date
    }

    fun format(epochMilliseconds: Long): String {
        val localDateTime = toLocalDateTime(epochMilliseconds)
        return "${localDateTime.year}-${
            localDateTime.monthNumber.toString().padStart(2, '0')
        }-${localDateTime.dayOfMonth.toString().padStart(2, '0')}"
    }

    private fun toLocalDateTime(epochMilliseconds: Long): LocalDateTime {
        val instant = Instant.fromEpochMilliseconds(epochMilliseconds)
        val localDateTime = instant.toLocalDateTime(TimeZone.currentSystemDefault())
        return localDateTime
    }
}