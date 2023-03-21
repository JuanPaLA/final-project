package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.Post
import com.proyecto404.finalProjectJP.core.domain.repositories.Posts
import org.example.final_project_jp.infrastructure.jooq.generated.Sequences
import org.example.final_project_jp.infrastructure.jooq.generated.Tables.POSTS
import org.jooq.impl.DSL

class JooqPosts(private val credentials: Credentials) : Posts {
    private fun context() = DSL.using(credentials.url, credentials.user, credentials.password)

    override fun nextId() = context().nextval(Sequences.POSTS_ID_SEQ)

    override fun get(id: Int): Post {
        return context()
            .selectFrom(POSTS)
            .where(POSTS.ID.eq(id))
            .fetch()
            .map { Post(it.id, it.userid, it.content, it.date) }
            .single()
    }

    override fun get(userName: String): List<Post> {
        return context()
            .selectFrom(POSTS)
            .where(POSTS.USERID.eq(userName))
            .orderBy(POSTS.DATE.desc())
            .fetch()
            .map { Post(it.id, it.content, it.userid, it.date) }
    }

    override fun get(usersNames: List<String>): List<Post> {
        return context()
            .selectFrom(POSTS)
            .where(POSTS.USERID.`in`(usersNames))
            .fetch()
            .map { Post(it.id, it.content, it.userid, it.date) }
    }

    override fun add(post: Post) {
        context()
            .insertInto(POSTS, POSTS.ID, POSTS.USERID, POSTS.CONTENT, POSTS.DATE)
            .values(post.id, post.userId, post.content, post.date)
            .execute()
    }

    override fun remove(postId: Int) {
        context()
            .deleteFrom(POSTS)
            .where(POSTS.ID.eq(postId))
            .execute()
    }
}
