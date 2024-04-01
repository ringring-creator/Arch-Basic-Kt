package com.ring.ring.data.repository

import com.ring.ring.data.User
import com.ring.ring.data.db.UserDataSource
import com.ring.ring.di.DataModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepository(
    private val dataSource: UserDataSource = DataModules.userDataSource,
) {
    suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }

    suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.upsert(user = user)
    }

    suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}