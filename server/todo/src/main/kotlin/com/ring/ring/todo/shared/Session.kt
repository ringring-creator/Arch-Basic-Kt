package com.ring.ring.user.shared

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: Long,
    val credential: String,
)