package com.ring.ring.repository

import com.ring.ring.db.UserDataSource
import com.ring.ring.di.DataModules
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface UserRepository {
    suspend fun get(id: Long): User
    suspend fun loadId(user: User): Long?
    suspend fun save(user: User)
    suspend fun delete(id: Long)
}

class DefaultUserRepository(
    private val dataSource: UserDataSource = DataModules.userDataSource,
) : UserRepository {
    override suspend fun get(id: Long): User = withContext(Dispatchers.IO) {
        dataSource.get(id = id)
    }

    override suspend fun loadId(user: User): Long? = withContext(Dispatchers.IO) {
        dataSource.loadId(user)
    }

    override suspend fun save(user: User) = withContext(Dispatchers.IO) {
        dataSource.upsert(user = user)
    }

    override suspend fun delete(id: Long) = withContext(Dispatchers.IO) {
        dataSource.delete(id = id)
    }
}