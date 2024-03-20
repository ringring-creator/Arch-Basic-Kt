package com.ring.ring.ui.user

import kotlinx.html.*

fun HTML.loginView() {
    head {
        title {
            buildString {
                append("Login")
            }
        }
    }
    body {
        form(action = "api/login", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
            p {
                +"Email:"
                textInput(name = "email")
            }
            p {
                +"Password:"
                passwordInput(name = "password")
            }
            p {
                submitInput { value = "Login" }
            }
        }
        p {
            +"Don't have an account? "
            a(href = "create/") {
                + "Sign up"
            }
        }
    }
}