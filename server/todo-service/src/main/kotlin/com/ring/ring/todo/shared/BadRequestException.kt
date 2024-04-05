package com.ring.ring.todo.shared

internal class BadRequestException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)