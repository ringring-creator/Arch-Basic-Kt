package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import kotlinx.html.*

fun HTML.logoutView() {
    head {
        title {
            buildString {
                append("Logout")
            }
        }
        bootstrap()
    }
    body {
        p {
            +"Do you want to logout?"
        }
        form(
            action = "api/logout",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            p {
                submitInput { value = "Yes" }
            }
        }
        form(
            action = "mypage",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            p {
                submitInput { value = "No" }
            }
        }
    }
}