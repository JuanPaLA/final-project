package com.proyecto404.finalProjectJP.core.infraestructure.persistence

import com.proyecto404.finalProjectJP.core.domain.User
import com.proyecto404.finalProjectJP.core.domain.UserId
import com.proyecto404.finalProjectJP.core.domain.Users

class InMemoryUsers: Users {
    override fun add(user: User) {
        TODO("Not yet implemented")
    }

    override fun get(userId: UserId): User? {
        TODO("Not yet implemented")
    }

    override fun remove(userId: UserId) {
        TODO("Not yet implemented")
    }

    override fun all(): List<User> {
        TODO("Not yet implemented")
    }

    override fun update(user: User) {
        TODO("Not yet implemented")
    }
}