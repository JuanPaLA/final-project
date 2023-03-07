package com.proyecto404.finalProjectJP.core.infraestructure.persistence.inMemory

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.Posts
import com.proyecto404.finalProjectJP.core.domain.services.PostNotFoundError
import com.proyecto404.finalProjectJP.core.domain.exceptions.EmptyPostError

class InMemoryPosts: Posts {
    private var nextId = 1
    val posts = mutableSetOf<Post>()

    override fun nextId() = nextId++

    override fun get(id: Int) = posts.singleOrNull { it.id == id } ?: throw PostNotFoundError()

    override fun get(userName: String) = posts.filter { it.userId == userName }

    override fun get(usersNames: List<String>) = posts.filter { usersNames.contains(it.userId) }

    override fun add(post: Post) {
        if (post.content.isNullOrEmpty()) throw EmptyPostError()
        else posts.add(post)
    }

    override fun remove(postId: Int) {
        val post = posts.single { it.id == postId }
        posts.remove(post)
    }
}