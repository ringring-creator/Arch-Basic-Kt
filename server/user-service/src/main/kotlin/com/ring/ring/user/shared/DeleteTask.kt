package com.ring.ring.user.shared

data class DeleteTask(
    val type: Type,
    val userId: Long
) {
    sealed class Type {
        data object User : Type()
        data object Todo : Type()
        data object Session : Type()
    }
}