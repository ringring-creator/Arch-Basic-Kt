package com.ring.ring.session.login

class LoginFailureException(
    message: String? = null,
    throwable: Throwable? = null
) : Throwable(message, throwable)