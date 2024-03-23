package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.user.GetUser
import kotlinx.html.*

fun HTML.mypageView(res: GetUser.Res) {
    head {
        title {
            buildString {
                append("My Page")
            }
        }
        bootstrap()
    }
    body {
        p {
            label {
                +"Email: "
                +res.user.email
            }
        }
        form(
            action = "logout",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            p {
                submitInput { value = "Logout" }
            }
        }
        form(
            action = "edit",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            p {
                submitInput { value = "Edit" }
            }
        }
        form(
            action = "delete",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            hiddenInput(name = "id") {
                value = res.user.id
            }
            p {
                submitInput { value = "Withdrawal" }
            }
        }
    }
}