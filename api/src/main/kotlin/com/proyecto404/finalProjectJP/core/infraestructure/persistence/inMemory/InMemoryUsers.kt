package com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.repositories.Users
import com.proyecto404.finalProjectJP.core.domain.exceptions.UserNotFoundError
import com.proyecto404.finalProjectJP.core.domain.exceptions.RepeatedUsernameError

var nextId = 1

class InMemoryUsers: Users {
    private var id = nextId++
    private val users: MutableList<User> = mutableListOf()

    override fun add(user: User) {
        if (! users.any { it.name == user.name }) users.add(user)
        else throw RepeatedUsernameError()
    }

    override fun delete(userName: String) {
        users.removeIf { it.name == userName }
    }

    override fun update(user: User) {
        users.removeIf { it.name == user.name }
        users.add(user)
    }

    override fun nextId(): Int {
        return id
    }

    override fun get(userName: String) = users.singleOrNull { it.name == userName } ?: throw UserNotFoundError()

    override fun get(): List<User> {
        return this.users
    }
}
