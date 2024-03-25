package com.ring.ring.exception

class BadRequestException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)