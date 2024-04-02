package com.ring.ring.user.shared

data class Session(
    val userId: Long,
    val credential: String,
)