package com.proyecto404.finalProjectJP.core.domain.repositories

import com.proyecto404.finalProjectJP.core.domain.Post

interface Posts {
    fun nextId(): Int
    fun get(id: Int): Post
    fun get(userName: String): List<Post>
    fun get(usersNames: List<String>): List<Post>
    fun add(post: Post)
    fun remove(postId: Int)
}

fun RepositoryProvider.posts() = this.get<Posts>()
