package com.ring.ring.session.shared

internal class LoginFailureException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)