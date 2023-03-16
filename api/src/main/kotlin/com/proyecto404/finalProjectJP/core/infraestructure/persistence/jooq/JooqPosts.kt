package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.repositories.Posts

class JooqPosts(credentials: Credentials): Posts {
    override fun nextId(): Int {
        TODO("Not yet implemented")
    }

    override fun get(id: Int): Post {
        TODO("Not yet implemented")
    }

    override fun get(userName: String): List<Post> {
        TODO("Not yet implemented")
    }

    override fun get(usersNames: List<String>): List<Post> {
        TODO("Not yet implemented")
    }

    override fun add(post: Post) {
        TODO("Not yet implemented")
    }

    override fun remove(postId: Int) {
        TODO("Not yet implemented")
    }
}
