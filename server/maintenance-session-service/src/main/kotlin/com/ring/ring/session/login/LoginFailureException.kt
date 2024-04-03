package com.ring.ring.session.login

internal class LoginFailureException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)