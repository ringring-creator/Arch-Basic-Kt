package com.ring.ring.data

import com.ring.ring.usecase.user.GetUser

data class User(
    val id: Long?,
    val email: String,
    val password: String,
) {
    fun toGetUser(): GetUser.Res.User {
        return GetUser.Res.User(
            id = id ?: throw IllegalStateException(),
            email = email,
            password = password
        )
    }
}