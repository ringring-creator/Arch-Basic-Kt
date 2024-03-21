package com.ring.ring.ui.user

import kotlinx.html.*

fun HTML.signUpView() {
    head {
        title {
            buildString {
                append("Sign Up")
            }
        }
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