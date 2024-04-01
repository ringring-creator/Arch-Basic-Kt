package com.ring.ring.usecase.todo

import com.ring.ring.data.Session
import com.ring.ring.data.Todo
import com.ring.ring.data.repository.SessionRepository
import com.ring.ring.data.repository.TodoRepository
import com.ring.ring.di.DataModules
import com.ring.ring.usecase.UseCase
import com.ring.ring.util.DateUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.datetime.LocalDate

class EditTodo(
    private val todoRepository: TodoRepository = DataModules.todoRepository,
    private val sessionRepository: SessionRepository = DataModules.sessionRepository,
) : UseCase<EditTodo.Req, EditTodo.Res>() {
    override suspend fun execute(req: Req): Res = withContext(Dispatchers.Default) {
        val session = sessionRepository.get() ?: throw Exception()
        val todo = convertTodo(req, session)
        todoRepository.edit(todo, session)
        return@withContext Res()
    }

    private fun convertTodo(
        req: Req,
        session: Session
    ) = Todo(
        id = req.id,
        title = req.title,
        description = req.description,
        done = req.done,
        deadline = req.localDateDeadline(),
        userId = session.userId,
    )

    data class Req(
        val id: Long,
        val title: String,
        val description: String,
        val done: Boolean,
        val deadline: Long,
    ) : UseCase.Req {
        fun localDateDeadline(): LocalDate = DateUtil.toLocalDate(deadline)
    }

    class Res : UseCase.Res
}