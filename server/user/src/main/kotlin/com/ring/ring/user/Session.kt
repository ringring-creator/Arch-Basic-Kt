package com.ring.ring.user

data class Session(
    val userId: Long,
    val credential: String,
)