package com.ring.ring.ui.todo

import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.deleteTodoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Delete Todo")
            }
        }
    }
    body {
        p {
            +"Do you want to delete the ${res.todo.title}?"
        }
        form(
            action = "api/delete",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            hiddenInput(name = "id") {
                value = res.todo.id
            }
            p {
                submitInput { value = "Yes" }
            }
        }
        form(
            action = "todo/list",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            p {
                submitInput { value = "No" }
            }
        }
    }
}