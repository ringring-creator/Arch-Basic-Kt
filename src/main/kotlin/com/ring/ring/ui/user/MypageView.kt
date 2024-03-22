package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.user.GetUser
import kotlinx.html.*

fun HTML.mypageView(res: GetUser.Res) {
    head {
        title {
            buildString {
                append("Todo")
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
            action = "user/logout",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            hiddenInput(name = "id") {
                value = res.user.id
            }
            p {
                submitInput { value = "Logout" }
            }
        }
        form(
            action = "user/edit",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            hiddenInput(name = "id") {
                value = res.user.id
            }
            p {
                submitInput { value = "Edit" }
            }
        }
        form(
            action = "todo/delete",
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