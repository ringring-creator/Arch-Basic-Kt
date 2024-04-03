package com.ring.ring.user.edit

import com.ring.ring.user.shared.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class EditUserRepository(
    private val dataSource: EditUserDataSource = EditUserModules.editUserDataSource,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.update(user = user)
    }
}