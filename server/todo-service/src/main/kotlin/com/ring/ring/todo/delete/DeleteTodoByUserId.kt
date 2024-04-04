package com.ring.ring.todo.delete

import com.ring.ring.todo.shared.UseCase
import kotlinx.serialization.Serializable

internal class DeleteTodoByUserId(
    private val repository: DeleteTodoRepository = DeleteTodoModules.deleteTodoRepository,
) : UseCase<DeleteTodoByUserId.Req, DeleteTodoByUserId.Res>() {
    override suspend fun execute(req: Req): Res {
        repository.deleteByUserId(req.userId)
        return Res()
    }

    @Serializable
    data class Req(
        val userId: Long,
    ) : UseCase.Req

    class Res : UseCase.Res
}