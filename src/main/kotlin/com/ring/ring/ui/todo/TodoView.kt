package com.ring.ring.ui.todo

import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.todoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Todo")
            }
        }
    }
    body {
        p {
            label {
                +"Title: "
                +res.todo.title
            }
        }
        p {
            label {
                +"Description: "
                +res.todo.description
            }
        }
        p {
            label {
                +"Done: "
                +res.todo.done
            }
        }
        p {
            label {
                +"Deadline: "
                +res.todo.deadline
            }
        }
        form(
            action = "todo/edit",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.get
        ) {
            hiddenInput(name = "id") {
                value = res.todo.id
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
                value = res.todo.id
            }
            p {
                submitInput { value = "Delete" }
            }
        }
    }
}