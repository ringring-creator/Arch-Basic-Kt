package com.ring.ring.ui.todo

import com.ring.ring.ui.bootstrap
import com.ring.ring.usecase.todo.GetTodoList
import kotlinx.html.*

fun HTML.todoListView(res: GetTodoList.Res) {
    head {
        title {
            buildString {
                append("Todo List")
            }
        }
        bootstrap()
    }
    body {
        todoNavBar()
        div(classes = "container mt-4") {
            h1(classes = "h3 mb-4 text-center") { +"Todo List" }
            res.todoList.forEach { todo ->
                a(href = "/todo?id=${todo.id}", classes = "card mb-3 p-2 text-decoration-none text-dark") {
                    div(classes = "d-flex justify-content-between align-items-center") {
                        div {
                            input(type = InputType.checkBox, classes = "form-check-input me-2") {
                                checked = todo.done
                                id = "todo-${todo.id}"
                            }
                            label(classes = "form-check-label") {
                                htmlFor = "todo-${todo.id}"
                                +todo.title
                            }
                        }
                        p(classes = "mb-0") { +"Deadline: ${todo.deadline}" }
                    }
                }
            }
            form(
                action = "create",
                encType = FormEncType.applicationXWwwFormUrlEncoded,
                method = FormMethod.get,
                classes = "mt-3"
            ) {
                button(classes = "btn btn-primary", type = ButtonType.submit) { +"Create New Todo" }
            }
        }
    }
}