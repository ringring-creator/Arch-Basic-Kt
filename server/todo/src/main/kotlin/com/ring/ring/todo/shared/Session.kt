package com.ring.ring.todo.shared

internal data class Session(
    val userId: Long,
    val credential: String,
)