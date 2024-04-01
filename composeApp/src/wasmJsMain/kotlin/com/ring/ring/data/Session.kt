package com.ring.ring.data

import kotlinx.serialization.Serializable

@Serializable
data class Session(
    val userId: Long,
    val credential: String,
)