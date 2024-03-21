package com.ring.ring.ui.todo

import kotlinx.html.*

fun HTML.createTodoView(userId: Long) {
    head {
        title {
            buildString {
                append("Create Todo")
            }
        }
    }
    body {
        form(
            action = "api",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) { // メソッドをPOSTに変更
            p {
                +"Title: "
                textInput(name = "title") { required = true } // 必須フィールド
            }
            p {
                +"Description: "
                textInput(name = "description")
            }
            p {
                +"Done: "
                checkBoxInput(name = "done")
            }
            p {
                +"Deadline: "
                // dateInputはKotlinx.htmlにはないため、textInputを使用してtypeをdateに設定
                textInput(name = "deadline") {
                    attributes["type"] = "date"
                }
            }
            p {
                // userIdを隠しフィールドとして送信
                hiddenInput(name = "userId") {
                    value = userId.toString()
                }
            }
            p {
                submitInput { value = "Create" }
            }
        }
    }
}