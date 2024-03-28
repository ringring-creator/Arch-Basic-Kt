package com.ring.ring.exception

class LoginFailureException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)