package com.ring.ring.user.shared

internal data class Session(
    val userId: Long,
    val credential: String,
)