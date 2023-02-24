package com.proyecto404.finalProjectJP.core.inMemory

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.Users

class InMemoryUsers: Users {
    private val users: MutableList<User> = mutableListOf()

    override fun add(user: User) {
        users.add(user)
    }

    override fun get(userName: String) = users.singleOrNull { it.name == userName } ?: throw UserNotFoundError()
}
