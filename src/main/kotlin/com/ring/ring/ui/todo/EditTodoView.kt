package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodo
import kotlinx.html.*

fun HTML.editTodoView(res: GetTodo.Res) {
    head {
        title {
            buildString {
                append("Edit Todo")
            }
        }
        bootstrap()
    }
    body {
        form(
            action = "api/edit",
            encType = FormEncType.applicationXWwwFormUrlEncoded,
            method = FormMethod.post
        ) {
            p {
                +"Title: "
                textInput(name = "title") {
                    required = true
                    value = res.todo.title
                }
            }
            p {
                +"Description: "
                textInput(name = "description") {
                    value = res.todo.description
                }
            }
            p {
                +"Done: "
                checkBoxInput(name = "done") {
                    value = res.todo.done
                }
            }
            p {
                +"Deadline: "
                textInput(name = "deadline") {
                    attributes["type"] = "date"
                    value = res.todo.deadline
                }
            }
            p {
                hiddenInput(name = "userId") {
                    value = res.todo.userId
                }
                hiddenInput(name = "id") {
                    value = res.todo.id
                }
            }
            p {
                submitInput { value = "Edit" }
            }
        }
    }
}