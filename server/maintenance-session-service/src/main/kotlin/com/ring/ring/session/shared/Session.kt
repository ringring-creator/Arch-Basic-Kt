package com.ring.ring.session.shared

import kotlinx.serialization.Serializable

@Serializable
internal data class Session(
    val userId: Long,
    val credential: String,
)