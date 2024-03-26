package com.ring.ring.ui.user

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.user.GetUser
import kotlinx.html.*

fun HTML.withdrawalUserView(res: GetUser.Res) {
    head {
        title {
            buildString {
                append("Withdrawal")
            }
        }
        bootstrap()
    }
    body(classes = "d-flex justify-content-center") {
        style = "min-height: 100vh; padding: 20px;"
        div(classes = "text-center") {
            h1(classes = "h3 mb-3 fw-normal text-center") { +"Withdrawal" }
            p(classes = "text-center") {
                +"Do you really want to withdrawal?"
            }
            div(classes = "d-inline-block") {
                form(
                    action = "api/delete",
                    encType = FormEncType.applicationXWwwFormUrlEncoded,
                    method = FormMethod.post,
                    classes = "d-inline",
                ) {
                    hiddenInput(name = "id") { value = res.user.id }
                    button(classes = "btn btn-lg btn-danger me-2", type = ButtonType.submit) {
                        +"Withdrawal"
                    }
                }
                form(
                    action = "mypage",
                    encType = FormEncType.applicationXWwwFormUrlEncoded,
                    method = FormMethod.get,
                    classes = "d-inline",
                ) {
                    button(classes = "btn btn-lg btn-secondary", type = ButtonType.submit) {
                        +"No"
                    }
                }
            }
        }
    }
}