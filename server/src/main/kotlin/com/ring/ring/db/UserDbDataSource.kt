package com.ring.ring.db

import com.ring.ring.di.DataModules
import com.ring.ring.repository.User
import db.UserQueries
import db.UserTable

interface UserDataSource {
    fun get(id: Long): User
    fun loadId(user: User): Long?
    fun upsert(user: User)
    fun delete(id: Long)
}

class UserDbDataSource(
    private val queries: UserQueries = DataModules.db.userQueries
) : UserDataSource {
    override fun get(id: Long): User = queries
        .selectById(id)
        .executeAsOne()
        .let { convert(it) }

    override fun loadId(user: User): Long? = queries
        .selectIdByEmailAndPassword(user.email, user.password)
        .executeAsOneOrNull()

    override fun upsert(user: User) {
        if (user.id == null) {
            insert(user = user)
        } else {
            update(user = user)
        }
    }

    override fun delete(id: Long) = queries.delete(id)

    private fun insert(user: User) = queries.insert(
        email = user.email,
        password = user.password,
    )

    private fun update(user: User) {
        val id = user.id ?: throw IllegalStateException()
        queries.update(
            email = user.email,
            password = user.password,
            id = id,
        )
    }

    private fun convert(table: UserTable) = User(
        id = table.id,
        email = table.email,
        password = table.password,
    )
}