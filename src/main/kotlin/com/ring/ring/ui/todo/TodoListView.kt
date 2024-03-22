package com.ring.ring.ui.todo

import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.html.*

fun HTML.todoListView(res: GetTodoList.Res) {
    head {
        title {
            buildString {
                append("Todo List")
            }
        }
    }
    body {
        res.todoList.forEach { todo ->
            a(href = "/todo?id=${todo.id}", classes = "card") {
                div {
                    input(type = InputType.checkBox) {
                        checked = todo.done
                        id = "todo-${todo.id}"
                    }
                    label {
                        htmlFor = "todo-${todo.id}"
                        +todo.title
                    }
                    p { +"Deadline: ${todo.deadline}" }
                }
            }
        }
        form(action = "create", encType = FormEncType.applicationXWwwFormUrlEncoded, method = FormMethod.get) {
            p {
                submitInput { value = "Create" }
            }
        }
    }
}