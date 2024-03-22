package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.loginView() {
    head {
        title {
            buildString {
                append("Login")
            }
        }
        bootstrap()
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
            a(href = "signup") {
                + "Sign up"
            }
        }
    }
}