package com.ring.ring.session.login

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val email: String,
    val password: String,
)