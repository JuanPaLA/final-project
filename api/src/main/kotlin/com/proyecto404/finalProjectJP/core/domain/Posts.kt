package com.proyecto404.finalProjectJP.core.domain

interface Posts {
    fun nextId(): Int
    fun get(id: Int): Post
    fun get(userName: String): List<Post>
    fun get(usersNames: List<String>): List<Post>
    fun add(post: Post)
    fun remove(postId: Int)
}
