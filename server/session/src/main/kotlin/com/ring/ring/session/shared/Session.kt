package com.ring.ring.session.shared

internal data class Session(
    val userId: Long,
    val credential: String,
)