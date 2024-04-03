package com.ring.ring.todo.shared

import app.cash.sqldelight.ColumnAdapter
import kotlinx.datetime.LocalDate

class DeadlineAdapter : ColumnAdapter<LocalDate, String> {
    override fun decode(databaseValue: String): LocalDate {
        return LocalDate.parse(databaseValue)
    }

    override fun encode(value: LocalDate): String {
        return value.toString()
    }
}