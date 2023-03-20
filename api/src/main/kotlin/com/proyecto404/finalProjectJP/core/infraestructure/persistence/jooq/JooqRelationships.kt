package com.proyecto404.finalProjectJP.core.infraestructure.persistence.jooq

import com.proyecto404.finalProjectJP.core.domain.Relationship
import com.proyecto404.finalProjectJP.core.domain.repositories.Relationships
import org.example.final_project_jp.infrastructure.jooq.generated.Tables.FOLLOWS
import org.jooq.impl.DSL

class JooqRelationships(private val credentials: Credentials) : Relationships {
    private fun context() = DSL.using(credentials.url, credentials.user, credentials.password)

    override fun add(relationship: Relationship) {
        val follower = relationship.follower.name
        val following = relationship.following.name

        if (context()
                .selectFrom(FOLLOWS)
                .where(FOLLOWS.FOLLOWER.eq(follower))
                .and(FOLLOWS.FOLLOWEE.eq(following))
                .fetch()
                .isEmpty()
        ) {
            context()
                .insertInto(FOLLOWS)
                .set(FOLLOWS.FOLLOWER, follower)
                .set(FOLLOWS.FOLLOWEE, following)
                .execute()
        }
    }

    override fun getFollowings(followings: String): List<String> {
        return context()
            .selectFrom(FOLLOWS)
            .where(FOLLOWS.FOLLOWER.eq(followings))
            .fetch()
            .map { it.followee }
    }

    override fun getFollowers(followers: String): List<String> {
        return context()
            .selectFrom(FOLLOWS)
            .where(FOLLOWS.FOLLOWEE.eq(followers))
            .fetch()
            .map { it.follower }
    }

    override fun remove(relationship: Relationship) {
        val follower = relationship.follower.name
        val following = relationship.following.name

        context()
            .deleteFrom(FOLLOWS)
            .where(FOLLOWS.FOLLOWER.eq(follower))
            .and(FOLLOWS.FOLLOWEE.eq(following))
            .execute()
    }
}
