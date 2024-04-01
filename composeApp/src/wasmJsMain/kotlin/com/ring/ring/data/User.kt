package com.ring.ring.data

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: Long?,
    val email: String,
    val password: String,
)