package com.ring.ring.todo.edit

internal class BadRequestException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)