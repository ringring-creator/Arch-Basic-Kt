package com.ring.ring.user.withdrawal

import com.ring.ring.user.shared.DeleteTask
import com.ring.ring.user.shared.SharedModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

internal class WithdrawalWorker(
    private val deleteTaskRepository: DeleteTaskRepository = SharedModules.deleteTaskRepository,
    private val userRepository: WithdrawalUserRepository = WithdrawalUserModules.withdrawalUserRepository,
    private val todoRepository: DeleteTodoRepository = WithdrawalUserModules.todoRepository,
    private val sessionRepository: DeleteSessionRepository = WithdrawalUserModules.sessionRepository,
) {
    suspend fun execute() = withContext(Dispatchers.IO) {
        while (true) {
            deleteTaskRepository.getTasks().forEach { task ->
                executeTask(task)
            }
            delay(50000)
        }
    }

    private suspend fun executeTask(task: DeleteTask) {
        when (task.type) {
            DeleteTask.Type.User -> {
                userRepository.delete(task.userId)
                deleteTaskRepository.delete(task)
            }

            DeleteTask.Type.Todo -> {
                todoRepository.delete(task.userId)
                deleteTaskRepository.delete(task)
            }

            DeleteTask.Type.Session -> {
                sessionRepository.delete(task.userId)
                deleteTaskRepository.delete(task)
            }
        }
    }
}