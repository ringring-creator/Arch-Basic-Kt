package com.ring.ring.repository

import kotlinx.datetime.LocalDate
import kotlinx.serialization.Serializable

@Serializable
data class Todo(
    val id: Long?,
    val title: String,
    val description: String,
    val done: Boolean,
    val deadline: LocalDate,
    val userId: Long,
)