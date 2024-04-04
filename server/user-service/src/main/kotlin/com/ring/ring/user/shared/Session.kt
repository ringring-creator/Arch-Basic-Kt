package com.ring.ring.user.shared

import kotlinx.serialization.Serializable

@Serializable
internal data class Session(
    val userId: Long,
    val credential: String,
)