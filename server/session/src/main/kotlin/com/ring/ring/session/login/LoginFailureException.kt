package com.ring.ring.com.ring.ring.login

class LoginFailureException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)