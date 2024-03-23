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
    body {
        p {
            +"Do you want to withdrawal?"
        }
        form(
            action = "api/delete",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            p {
                hiddenInput(name = "id") {
                    value = res.user.id
                }
            }
            p {
                submitInput { value = "Withdrawal" }
            }
        }
        form(
            action = "user/mypage",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            p {
                submitInput { value = "No" }
            }
        }
    }
}