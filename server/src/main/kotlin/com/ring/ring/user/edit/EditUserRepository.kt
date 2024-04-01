package com.ring.ring.user.edit

import com.ring.ring.data.User
import com.ring.ring.di.DataModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class EditUserRepository(
    private val dataSource: EditUserDataSource = DataModules.editUserDataSource,
) {
    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.update(user = user)
    }
}