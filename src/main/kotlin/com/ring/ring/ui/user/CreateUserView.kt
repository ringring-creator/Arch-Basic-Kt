package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.signUpView() {
    head {
        title {
            buildString {
                append("Sign Up")
            }
        }
        bootstrap()
    }
    body {
        form(action = "api", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.post) {
            p {
                +"Email:"
                textInput(name = "email")
            }
            p {
                +"Password:"
                passwordInput(name = "password")
            }
            p {
                submitInput { value = "Create" }
            }
        }
    }
}